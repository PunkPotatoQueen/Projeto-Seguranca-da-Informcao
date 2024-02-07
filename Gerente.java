import java.util.ArrayList;
import java.util.List;

public class Gerente<Funcionario> {
    private String nome;
    private int idade;
    private String endereco;
    private String cpf;
    private int departamento;
    private List<Funcionario> funcionarios;

    public Gerente(String nome, int idade, String endereco, String cpf, int departamento) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.cpf = cpf;
        this.departamento = departamento;
        this.funcionarios = new ArrayList<>();
    }

    // Métodos Get e Set (conforme necessário)

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    // Métodos adicionais para adicionar e remover funcionários
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    // Método para exibir informações do gerente e seus funcionários
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
        System.out.println("CPF: " + cpf);
        System.out.println("Departamento: " + departamento);
        System.out.println("Funcionários: " + funcionarios);
    }
}
