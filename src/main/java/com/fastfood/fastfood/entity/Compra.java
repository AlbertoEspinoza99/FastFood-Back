package com.fastfood.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "compra")
@JsonIgnoreProperties("listaDetalleCompra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private Double total;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleCompra> listaDetalleCompra = new ArrayList<>();

    public void addDetalle(DetalleCompra detalleCompra) {
        listaDetalleCompra.add(detalleCompra);
        detalleCompra.setCompra(this);
    }

    public void removeDetalle(DetalleCompra detalleCompra) {
        listaDetalleCompra.remove(detalleCompra);
        detalleCompra.setCompra(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleCompra> getListaDetalleCompra() {
		return listaDetalleCompra;
	}

	public void setListaDetalleCompra(List<DetalleCompra> listaDetalleCompra) {
		this.listaDetalleCompra = listaDetalleCompra;
	}
    
}
