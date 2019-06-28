package controllers;

/**
 * Implementação de um api para a aplicação de gestão de processos (simples), acessível via rest.
 * 
 * Apenas o CRUD de Responsavel
 * 
 * Todas as rotas foram mapeadas no arquivo routes.conf
 *   
 * @author efraimfarias 
 * @date 27/06/2019
 * @version 1.0
 * @copyright 
 * 
 */

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;
import java.util.List;
import java.util.ArrayList; 

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray; 

import models.Responsavel;
import play.libs.Json;
import play.mvc.*;

import io.ebean.*;

/*
 * Classe principal que interage com todas as chamadas via REST
 * Criando regras de validação de dados e ações o Banco de Dados
 *
 */
public class ResponsavelController extends Controller {

    /*
     * Onde será armazenado uma lista de Pre condições
     */
    ArrayList<JSONObject> preCondFails = new ArrayList<JSONObject>();
          
  
    /*
     * Todos os Responsáveis no sistema
     * 
     * Este método é usado para retornar responsáveis através de filtros e parametros pre definidos
     * 
     * @param Long page refere a partir de qual página retornará os dados
     * @param Long size é a quantidade máxima de registros por bloco
     *   
     * @return jsonNode com os registro confome os parametros e a quantidade total de registros
     * 
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result responsaveis(Long page, Long size) {

        //json com os registros e a quantidade
        JSONObject resultPageResponsavel = new JSONObject(); 

        //obtendo o body via json
        JsonNode json = request().body().asJson();

        String cpf = json.findPath("cpf").textValue();
        String nome = json.findPath("nome").textValue();
        String email = json.findPath("email").textValue();

        
        //String sql para obter todos os responsaveis
        String sql = "select cpf, to_char(data_nascimento, 'DD/MM/YYYY'), id, nome, email  from responsavel where cpf like :cpf or LOWER(nome) like LOWER(:nome) or LOWER(email) like LOWER(:email) ";

        //Query sendo montado com os critérios
        SqlQuery sqlQuery = Ebean.createSqlQuery(sql); 
        sqlQuery.setParameter("cpf",   "%" + cpf   + "%");
        sqlQuery.setParameter("nome",  "%" + nome  + "%");
        sqlQuery.setParameter("email", "%" + email + "%");

        //Quantidade total de responsaveis
        int counts = sqlQuery.findList().size(); 

        //Quantidades de registro limitados por paginas
        List<SqlRow> rows = sqlQuery
                            .setFirstRow(page.intValue() * size.intValue())
                            .setMaxRows(size.intValue())
                            .findList();


        //Registros da pagina atual
        resultPageResponsavel.put("records", rows);

        //Total de registros independente da pagina
        resultPageResponsavel.put("records_number", counts);

        JsonNode jsonNode = Json.toJson(resultPageResponsavel);
       
        return status(200, jsonNode);
    }

    /*
     * Criando um Responsavel
     * 
     * Este método é usado para criar um responsável através de dados que serão validados
     * 
     * Os campos de validação e preeenchimentos são passados atráves de Json 
     *   
     * @return status OK: criado ou com ressalvas nas pre condições
     * 
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result addResponsavel() {

        //obtendo o body via json
        JsonNode json = request().body().asJson();
                    
        String cpf = json.findPath("cpf").textValue();
        String nome = json.findPath("nome").textValue();
        String email = json.findPath("email").textValue();
        String data_nascimento = json.findPath("data_nascimento").textValue();

        //Verificando as precondições. -1, pois não encontrar nenhum ID - cadastro
        validaDados(-1, nome, cpf, email, data_nascimento);

        //Se existe é por que existe ao menos uma condição não satisfatório, retornando a falha
        if(preCondFails.size() > 0){
            JsonNode fails = Json.toJson(preCondFails);
            preCondFails = new ArrayList<JSONObject>();
            return status(412, fails);

        }
     
        //Criando um responsavel e atribuindo os valores validados
        Responsavel responsavel = new Responsavel();
        responsavel.cpf = cpf;
        responsavel.nome = nome;
        responsavel.email = email;
        responsavel.data_nascimento = new Date(data_nascimento);

        //Salvando o Registro de Responsavel
        responsavel.save();
        
        //Tudo certo
        return status(201);
    }
    

    /*
     * Alterando um Responsavel
     * 
     * Este método é usado para alterar um responsável através de dados que serão validados
     * Os campos de validação e preeenchimentos são passados atráves de Json 
     * 
     * @param long id O identificador do responsável
     *   
     * @return status OK: alterado ou com ressalvas nas pre condições
     * 
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateResponsavel(long id) {

        //Verificando se o Responsavel existe com o Id informado
        if( Responsavel.find.byId(id) != null ) {

            //Obtendo o responsavel atraves do id
            Responsavel responsavel = Responsavel.find.byId(id);

            //obtendo o body via json
            JsonNode json = request().body().asJson();
        
            String cpf = json.findPath("cpf").textValue();
            String nome = json.findPath("nome").textValue();
            String email = json.findPath("email").textValue();
            String data_nascimento = json.findPath("data_nascimento").textValue();

            //Verificando as precondições
            validaDados(id, nome, cpf, email, data_nascimento);

            //Se existe é por que existe ao menos uma condição não satisfatório, retornando a falha
            if(preCondFails.size() > 0){
                JsonNode fails = Json.toJson(preCondFails);
                preCondFails = new ArrayList<JSONObject>();
                return status(412, fails);

            }
        
            //Atribuindo os novos valores validados
            responsavel.cpf = cpf;
            responsavel.nome = nome;
            responsavel.email = email;
            responsavel.data_nascimento = new Date(data_nascimento);

            //Alterando um responsavel
            responsavel.update();

            //Tudo certo
            return status(200);

        } else { //Não existe o ID informado

            //Registrando o erro
            addInvalidos("responsavel.id", "inexistente", "O responsável é inexistente", "");
  
            JsonNode fails = Json.toJson(preCondFails);
            preCondFails = new ArrayList<JSONObject>();
        
            //Falha
            return status(405, fails);
        }
    }


    /*
     * Obtendo um Responsavel
     * 
     * Este método é usado para obter um responsável através do ID  
     * 
     * @param long id O identificador do responsável
     *   
     * @return status OK: encontrado ou não encontrado 
     * 
     */
    public Result responsavel(long id) {

        //Obtendo o responsavel atraves do id
        Responsavel responsavel = Responsavel.find.byId(id);

        //Se encontrou o responsavel
        if( responsavel != null) {
          
            JsonNode jsonNode = Json.toJson(responsavel);
            ObjectNode result = (ObjectNode) jsonNode;
            
            try {
         
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                //Usado pois o banco retorna número. 
                String dateString = format.format( responsavel.data_nascimento   );
                result.put("data_nascimento", dateString);
            } 
                catch (Exception e) {
            }
                       
            //Tudo certo
            return status( 200, result );
            
        } else {

            //ID não encontrado
            return status(404);
        }
    }
    

    /*
     * Deletando um Responsavel
     * 
     * Este método é usado para excluir responsável através do ID  
     * 
     * @param long id O identificador do responsável
     *   
     * @return status OK: excluido ou não encontrado ou registro com restrinções  
     * 
     */
    public Result deleteResponsavel(long id) {

        //status inicial 200. O Valor poderá ser alterado conforme condições de validação
        int statusNumber = 200;

        //Verificando se o Responsavel existe com o Id informado
        if( Responsavel.find.byId(id) != null ){
            
            //Estar vinculado a outros registros
            try {
            
                //Deletando o Responsavel
                Responsavel.find.ref(id).delete();

                //Deu certo
                return status(statusNumber);
                
            } catch (Exception e) {

                //Houve uma restrição. Poderá estar sendo vinculado à outros registros
                addInvalidos("responsavel.processo", "vinculadoProcesso", "O responsável está vinculado a um processo, não é possivel remover", "");

                //Falha. Pre condições
                statusNumber = 412;
            }

        } else {

            //ID não encontrado. Registrando o Erro 
            addInvalidos("responsavel.id", "inexistente", "O responsável é inexistente", "");

            //Falha
            statusNumber = 405;
        }

        JsonNode fails = Json.toJson(preCondFails);
        preCondFails = new ArrayList<JSONObject>();
        
        //Deu erro
        return status(statusNumber, fails);
    }
    


    /*
     * Verificando se existe um Cpf
     * 
     * Este método é usado para verificar se existe um cpf de um outro responsável  
     * 
     * @param long cpf a ser inserido ou alterado
     * @param long id do responsavel, caso seja uma alteração
     *   
     * @return Boolean true ou false  
     * 
     */
    private Boolean existeCpf(String cpf, long id) {

        //String sql para encontrar o cpf 
        String sql = " select responsavel.cpf from responsavel where responsavel.cpf = :cpf and responsavel.id != :id";

        //Adicionando os critérios
        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
        sqlQuery.setParameter("cpf", cpf);
        sqlQuery.setParameter("id",  id );
       
        List<SqlRow> list = sqlQuery.findList();

        //Se existe é por que encontrou
        if( list.size() > 0 ){
            return true;
        } else {   
            return false;
        }
        
    }

    /*
     * Validando os dados
     * 
     * Este método é usado para validar os dados conforme regra de negócio
     * 
     * A cada pre condição inválida é registrado o erro e então adicionado numa Lista de erros
     * 
     * @param long id do Responsavel, não é usado para validar apenas no caso de alteração
     * @param String nome 
     * @param String cpf
     * @param String email
     * @para String data_nascimento
     *   
     * @return
     * 
     */
    private void validaDados(long id, String nome, String cpf, String email, String data_nascimento){

        //Nome não pode ser vazio
        if( nome == "" ) {
            addInvalidos("responsavel.nome", "obrigatorio", "O nome é obrigatório", "");
        
        } else if( nome.length() > 75 ) { //o Nome não pode ter mais que 75 caracteres
            
            addInvalidos("responsavel.nome", "tamanhoMaximo", "O nome deve possuir no máximo {} caracteres", "75");
        }

        //Cpf não pode ser vazio
        if( cpf == "" ) {
            addInvalidos("responsavel.cpf", "obrigatorio", "O CPF é obrigatório", "");

        } else if (!cpf.matches("[0-9]+") || cpf.length() != 11) { //Somente números e exatos 11 números

            addInvalidos("responsavel.cpf", "invalido", "O CPF é inválido, o CPF deve possuir exatamente {} caracteres numéricos", "11");
  
        } else if ( existeCpf( cpf, id ) ) { //Não poderá existir um cpf igual já registtrado. Salvo alteração com o mesmo ID

            addInvalidos("responsavel.cpf", "duplicado", "O CPF é duplicado, já existe outro responsável com o CPF informado", "");

        }

        //Email não pode ser vazio
        if( email == "" ){
            addInvalidos("responsavel.email", "obrigatorio", "O e-mail é obrigatório", "");
        
        } else if( !email.matches("^(.+)@(.+)$") ) { //Validando o básico de um email

            addInvalidos("responsavel.email", "invalido", "O e-mail informado é inválido", "");

        } else if( email.length() > 65 ) { //Não pode ter mais que 65 caracteres

            addInvalidos("responsavel.email", "tamanhoMaximo", "O e-mail deve possuir no máximo {} caracteres", "65");

        } else if( existeEmail(email, id)) { //Não poderá existir um email igual já registrado. Salvo alteração com o mesmo ID

            addInvalidos("responsavel.email", "duplicado", "O e-mail é duplicado, já existe outro responsável com o e-mail informado", "");
        }

        try {
       
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");   
            Date dtNascimento = format.parse(data_nascimento);

            //A data de nascimento não pode ser maior que hoje
            if (dtNascimento.after(new Date())) {
                addInvalidos("responsavel.dataNascimento", "menorDataAtual", "A data de nascimento deve ser menor que a data atual", "");
            }
        } 
        catch (ParseException e) {
        }

    }

    /*
     * Adicionado os erros numa lista 
     * 
     * Este método é usado para registrar os erros encontrados numa Lista
     * 
     * A cada pre condição inválida a chamada desse método é feita
     * 
     * @param String local_erro
     * @param String erro 
     * @param String mensagem
     * @param String arg
     *   
     * @return
     * 
     */
    private void addInvalidos(String local_erro, String erro, String mensagem, String arg){
        JSONObject validationItem = new JSONObject();
        JSONArray args = new JSONArray(); 
        
        validationItem.put("local_erro", local_erro);
        validationItem.put("erro", erro);
        validationItem.put("mensagem", mensagem);

        //Vetor
        if( arg != "" ){
            args.add(Integer.parseInt(arg) );
        }
        validationItem.put("args", args);

        //Adicinando na lista de erros
        preCondFails.add(validationItem);

    }


    /*
     * Verificando se existe um email
     * 
     * Este método é usado para verificar se existe um email de um outro responsável  
     * 
     * @param String email a ser inserido ou alterado
     * @param long id do responsavel, caso seja uma alteração
     *   
     * @return Boolean true ou false  
     * 
     */
    private Boolean existeEmail(String email, long id) {

        //String sql para encontrar o email
        String sql = " select responsavel.email from responsavel where responsavel.email = :email and responsavel.id != :id";

        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
        sqlQuery.setParameter("email", email);
        sqlQuery.setParameter("id",  id );
       
        List<SqlRow> list = sqlQuery.findList();

        //Se existe é por que encontrou
        if( list.size() > 0 ){
            return true;
        } else {   
        return false;
        }

    }

    
   
}