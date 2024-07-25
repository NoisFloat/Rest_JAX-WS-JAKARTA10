package sv.edu.udb.model;

public class Concepto {
    private int id;
    private String nombre;
    private Categoria categoria;
    private int categoryId;
    private double valor;

    public Concepto() {}
    public Concepto(int id, String nombre, Categoria categoria, int categoryId, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.categoryId = categoryId;
        this.valor = valor;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
