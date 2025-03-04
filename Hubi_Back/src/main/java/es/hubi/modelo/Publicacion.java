package es.hubi.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PUBLICACIONES")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MENSAJE", nullable = false)
    private String mensaje;

    @Column(name = "FECHA", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")
    private Categoria categoria;

    @Lob
    @Column(name = "IMAGEN", columnDefinition = "LONGBLOB")
    private byte[] imagen; // Nuevo campo para la imagen

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public byte[] getImagen() { return imagen; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }
}
