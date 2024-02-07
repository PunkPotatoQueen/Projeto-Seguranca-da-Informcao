public class Cliente {
    private static String nome;
    private static int idade;
    private static String endereco;
    private static String numeroConta;
    private static String cpf;

    public Cliente(String nome, int idade, String endereco, String numeroConta, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.numeroConta = numeroConta;
        this.cpf = cpf;
    }

    // Métodos Get
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getCpf() {
        return cpf;
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

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método adicional para exibir informações
    public static void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
        System.out.println("Número da Conta: " + numeroConta);
        System.out.println("CPF: " + cpf);
    }
}
