import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { NgFor, CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { Publicaction } from '../../service/publication.service';
import { CategoryService } from '../../service/category.service';
import { Publicacion, Categoria } from '../../models/general.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule, NgFor],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tweetText: string = '';
  nombre: string = '';
  maxCharacters: number = 2000;
  publicaciones: Publicacion[] = [];
  categorias: Categoria[] = [];
  selectedCategoria: Categoria | null = null;
  selectedFilterCategoria: number | null = null;
  categoriaFiltrada: string = 'Publicaciones';
  closeComponent = true;
  selectedFile: File | null = null;

  constructor(
    private publicaction: Publicaction,
    private categoryService: CategoryService,
    private toastSvc: ToastrService
  ) {}

  ngOnInit(): void {
    this.obtenerCategorias();
    this.obtenerPublicaciones();
  }

  obtenerCategorias(): void {
    this.categoryService.getCategorias().subscribe(
      (response: Categoria[]) => {
        this.categorias = response;
      },
      (error: HttpErrorResponse) => {
        console.error('Error al cargar las categorías:', error);
      }
    );
  }

  obtenerPublicaciones(): void {
    this.publicaction.getPublicaciones().subscribe(
      (response: Publicacion[]) => {
        this.publicaciones = response;
      },
      (error: HttpErrorResponse) => {
        console.error('Error al cargar las publicaciones:', error);
      }
    );
  }

  filtrarPublicaciones(): void {
    if (this.selectedFilterCategoria) {
      this.publicaction.getPublicaciones(this.selectedFilterCategoria).subscribe(
        (response: Publicacion[]) => {
          this.publicaciones = response;
          const categoria = this.categorias.find(cat => cat.id === this.selectedFilterCategoria);
          this.categoriaFiltrada = categoria ? categoria.nombre : 'Publicaciones';
        },
        (error: HttpErrorResponse) => {
          console.error('Error al filtrar publicaciones:', error);
        }
      );
    } else {
      this.obtenerPublicaciones();
      this.categoriaFiltrada = 'Publicaciones';
    }
  }

  calcularTiempoTranscurrido(fecha: string | number | Date): string {
    const fechaPublicacion = new Date(fecha);
    const fechaActual = new Date();
    const diferencia = fechaActual.getTime() - fechaPublicacion.getTime();
    
    const segundos = Math.floor(diferencia / 1000);
    const minutos = Math.floor(segundos / 60);
    const horas = Math.floor(minutos / 60);
    const dias = Math.floor(horas / 24);

    if (dias > 0) {
      return `${dias} d`; 
    } else if (horas > 0) {
      return `${horas} h`; 
    } else if (minutos > 0) {
      return `${minutos} min`; 
    } else {
      return `${segundos} s`; 
    }
  }

  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  publicar(): void {
    if (!this.tweetText.trim() || !this.selectedCategoria || !this.nombre.trim()) return;

    const nuevaPublicacion: Publicacion = {
      idUsuario: 1,
      mensaje: this.tweetText,
      fecha: new Date().toISOString(),
      categoria: this.selectedCategoria,
      nombre: this.nombre
    };

    this.publicaction.createPublicacion(nuevaPublicacion, this.selectedFile).subscribe(
      (response: Publicacion) => {
        this.toastSvc.success('¡Publicación creada con éxito!', 'HUBI');

        if (
          !this.selectedFilterCategoria || 
          (response.categoria && response.categoria.id === this.selectedFilterCategoria)
        ) {
          this.publicaciones.unshift(response);
        }

        this.tweetText = '';
        this.selectedCategoria = null;
        this.nombre = ''; 
        this.selectedFile = null;
      },
      (error: HttpErrorResponse) => {
        console.error('Error al crear la publicación:', error);
      }
    );
  }
}
