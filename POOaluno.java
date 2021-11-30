import java.time.LocalDate;

public class POOaluno {
    private long RA;
    private String nome;
    private String sobrenome;
    private LocalDate nasimento;
    private int CPF;

    public long getRA() {
        return RA;
    }

    public void setRA(long RA) {
        this.RA = RA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getNasimento() {
        return nasimento;
    }

    public void setNasimento(LocalDate nasimento) {
        this.nasimento = nasimento;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}
