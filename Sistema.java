import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Veiculo> veiculos;
    private List<Cliente> clientes;
    private List<ClientePJ> listaDeClientesPJ;
    private List<Agencia> agencias;
    private List<LocadoraSantander> alugueis;

    public Sistema() {
        veiculos = new ArrayList<>();
        clientes = new ArrayList<>();
        listaDeClientesPJ = new ArrayList<>();
        agencias = new ArrayList<>();
        alugueis = new ArrayList<>();
    }

    public List<ClientePJ> getListaDeClientesPJ() {
        return listaDeClientesPJ;
    }

    // VEÍCULOS
    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculos.contains(veiculo)) {
            System.out.println("Veículo já cadastrado no sistema.");
            return;
        }
        veiculos.add(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    public Veiculo buscarVeiculoPorModelo(String parteDoNome) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getModelo().toLowerCase().contains(parteDoNome.toLowerCase())) {
                return veiculo;
            }
        }
        return null;
    }

    public void alterarVeiculo(String placaAntiga, String novaPlaca, String modelo, String novaCor) {
        Veiculo veiculo = buscarVeiculoPorModelo(modelo);
        if (veiculo != null && veiculo.getPlaca().equals(placaAntiga)) {
            veiculo.setPlaca(novaPlaca);
            veiculo.setCor(novaCor);
            System.out.println("Veículo alterado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado no sistema.");
        }
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado no sistema.");
        } else {
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
            }
        }
    }

    // AGÊNCIA
    public void cadastrarAgencia(Agencia agencia) {
        if (agencias.contains(agencia)) {
            System.out.println("Agência já cadastrada no sistema.");
            return;
        }
        agencias.add(agencia);
        System.out.println("Nova agência cadastrada com sucesso!");
    }

    public Agencia buscarAgenciaPorNome(String nome) {
        for (Agencia agencia : agencias) {
            if (agencia.getNome().equalsIgnoreCase(nome)) {
                return agencia;
            }
        }
        return null;
    }

    public Agencia buscarAgenciaPorCidade(String cidade) {
        for (Agencia agencia : agencias) {
            if (agencia.getCidade().equalsIgnoreCase(cidade)) {
                return agencia;
            }
        }
        return null;
    }

    public void alterarAgencia(String novoNome, String cidade, String novaCidade) {
        Agencia agencia = buscarAgenciaPorCidade(cidade);
        if (agencia != null) {
            agencia.setNome(novoNome);
            agencia.setCidade(novaCidade);
            System.out.println("Agência alterada com sucesso!");
        } else {
            System.out.println("Agência não encontrada no sistema.");
        }
    }

    public void listarAgencias() {
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência cadastrada no sistema.");
        } else {
            for (Agencia agencia : agencias) {
                System.out.println(agencia);
            }
        }
    }

    // CLIENTES
    public void cadastrarCliente(Cliente cliente) {
        if (clientes.contains(cliente)) {
            System.out.println("Cliente já cadastrado no sistema.");
            return;
        }
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");

        if (cliente instanceof ClientePJ) {
            listaDeClientesPJ.add((ClientePJ) cliente);
        }
    }

    public Cliente procurarClientePeloDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no sistema.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    // LOCAÇÃO
    public void listarLocacoes() {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhuma locação registrada no sistema.");
        } else {
            System.out.println("Lista de Locações:");
            for (LocadoraSantander aluguel : alugueis) {
                System.out.println(aluguel);
            }
        }
    }

    public void alugarVeiculo(String documentoCliente, String modeloVeiculo, String cidadeAgencia) {
        Cliente cliente = procurarClientePeloDocumento(documentoCliente);
        Veiculo veiculo = buscarVeiculoPorModelo(modeloVeiculo);
        Agencia agencia = buscarAgenciaPorCidade(cidadeAgencia);

        if (cliente == null) {
            System.out.println("Cliente não encontrado no sistema.");
            return;
        }
        if (veiculo == null) {
            System.out.println("Veículo não encontrado no sistema.");
            return;
        }
        if (agencia == null) {
            System.out.println("Agência não encontrada no sistema.");
            return;
        }
        if (veiculo.isAlugado()) {
            System.out.println("Veículo já está alugado.");
            return;
        }

        LocadoraSantander aluguel = new LocadoraSantander(veiculo, cliente, agencia, LocalDate.now());
        alugueis.add(aluguel);
        veiculo.alugar();
        System.out.println("Veículo alugado com sucesso!\n");
        System.out.println("Comprovante:");
        System.out.println("Dados do Cliente: ");
        System.out.println("Nome: " + aluguel.getCliente().getNome());
        System.out.println("Documento: " + aluguel.getCliente().getDocumento() + "\n");
        System.out.println("Dados do Veículo: ");
        System.out.println("Modelo: " + aluguel.getVeiculo().getModelo());
        System.out.println("Placa: " + aluguel.getVeiculo().getPlaca());
        System.out.println("Cor: " + aluguel.getVeiculo().getCor() + "\n");
        System.out.println("Alugado na Agência: " + aluguel.getAgencia().getNome() + ", na cidade de " + aluguel.getAgencia().getCidade() + "\n");
        System.out.println("Data do Aluguel: " + aluguel.getAluguelData());
    }

    public void entregarVeiculo(String documentoCliente, String modeloVeiculo, String nomeAgenciaDevolucao) {
        LocadoraSantander aluguelEncontrado = null;
        for (LocadoraSantander aluguel : alugueis) {
            if (aluguel.getCliente().getDocumento().equals(documentoCliente) &&
                    aluguel.getVeiculo().getModelo().equals(modeloVeiculo) &&
                    aluguel.getDevolucaoData() == null) {
                aluguelEncontrado = aluguel;
                break;
            }
        }

        if (aluguelEncontrado != null) {
            Agencia agenciaDevolucao = buscarAgenciaPorNome(nomeAgenciaDevolucao);
            if (agenciaDevolucao == null) {
                System.out.println("Agência para devolução não encontrada.");
                return;
            }

            aluguelEncontrado.entregarVeiculo(LocalDate.now(), agenciaDevolucao);
            System.out.println("Veículo entregue com sucesso!\n");
            System.out.println("Comprovante:");
            System.out.println("Dados do Cliente: ");
            System.out.println("Nome: " + aluguelEncontrado.getCliente().getNome());
            System.out.println("Documento: " + aluguelEncontrado.getCliente().getDocumento());
            System.out.println("Telefone: " + aluguelEncontrado.getCliente().getTelefone() + "\n");
            System.out.println("Dados do Veículo: ");
            System.out.println("Modelo: " + aluguelEncontrado.getVeiculo().getModelo());
            System.out.println("Placa: " + aluguelEncontrado.getVeiculo().getPlaca());
            System.out.println("Cor: " + aluguelEncontrado.getVeiculo().getCor() + "\n");
            System.out.println("Agência: " + aluguelEncontrado.getAgencia().getNome() + ", na cidade de " + aluguelEncontrado.getAgencia().getCidade() + "\n");
            System.out.println("Data do Aluguel: " + aluguelEncontrado.getAluguelData());
            System.out.println("Data de Devolução: " + aluguelEncontrado.getDevolucaoData() + "\n");
            System.out.printf("Valor Total: R$%.2f\n", aluguelEncontrado.getValorTotal());
        } else {
            System.out.println("Nenhum aluguel encontrado para este cliente e veículo.");
        }
    }
}