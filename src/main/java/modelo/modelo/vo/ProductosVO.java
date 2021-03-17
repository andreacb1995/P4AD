package modelo.vo;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class ProductosVO {

private List<ProductoVO> productos;
	
	public ProductosVO() {
		
	}
	
	public List<ProductoVO> getProducto() {
		return productos;
	}
	
	@XmlElement(name="producto")
	public void setProducto(List<ProductoVO> productos) {
		this.productos = productos;
	}
	
	
}
