import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgIf, NgFor, CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { Publicaction } from '../../service/publication'; // Ruta corregida
import { Publicacion } from '../../models/publicaction.model'; // Ruta corregida
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule, NgFor],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tweetText: string = '';
  maxCharacters: number = 2000;
  publicaciones: Publicacion[] = [];
  closeComponent = true;

  constructor(
    private publicaction: Publicaction,
    private toastSvc: ToastrService
  ) {}

  ngOnInit(): void {
    this.obtenerPublicaciones();
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



  publicar(): void {
    if (!this.tweetText.trim()) return;

    const nuevaPublicacion: Publicacion = {
      idUsuario: 1, // Ajusta según el usuario autenticado
      mensaje: this.tweetText,
      fecha: new Date().toISOString() // Agregar la fecha actual al crear la publicación
    };

    this.publicaction.createPublicacion(nuevaPublicacion).subscribe(
      (response: Publicacion) => {
        this.closeComponent = false;
        this.toastSvc.success('Publicación creada con éxito!', 'HUBI');
        this.publicaciones.unshift(response);
        this.tweetText = ''; // Limpia el textarea
      },
      (error: HttpErrorResponse) => {
        console.error('Error al crear la publicación:', error);
      }
    );
  }
}
