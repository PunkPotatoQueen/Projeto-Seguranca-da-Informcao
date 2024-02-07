import java.util.ArrayList;
import java.util.List;

public class Funcionario<Cliente> {
    private String nome;
    private int idade;
    private String endereco;
    private int matricula;
    private String cpf;
    private int departamento;
    private List<Cliente> clientes;

    public Funcionario(String nome, int idade, String endereco, String cpf, int departamento) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.cpf = cpf;
        this.departamento = departamento;
        this.clientes = new ArrayList<>();
    }

    // Métodos Get
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public int getDepartamento() {
        return departamento;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    // Métodos Set
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Métodos adicionais para adicionar e remover clientes
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    // Método adicional para exibir informações
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
        System.out.println("CPF: " + cpf);
        System.out.println("Departamento: " + departamento);
        System.out.println("Clientes: " + clientes);

    }
}
