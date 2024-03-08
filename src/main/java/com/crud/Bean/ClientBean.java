package com.crud.Bean;

import com.crud.DAO.Impl.DaoIml;
import com.crud.Model.Client;
import com.crud.Service.ClientService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
//@RequestScoped
@ViewScoped
public class ClientBean implements Serializable{
    private ClientService clientService = new ClientService(new DaoIml());
    private Client client = new Client();
    List<Client> clients = new ArrayList<Client>();
    private boolean editable;
    private boolean editableprevious;
    private boolean editablenext = true;


    private UIInput firstNameInput;
    private UIInput firstName1Input;
    private UIInput lastNameInput;
    private UIInput emailInput;
    private UIInput phoneInput;
    private UIInput passwordInput;
    private boolean editMode;
    private Client selectedClient;

    private int inputid;
    private String firstNameUInput;
    private String lastNameUInput;
    private String emailUInput;
    private String phoneUInput;
    private String passwordUInput;


    private int pageSize;
    private int currentPage;
    private int totalPages;

    public ClientBean() throws SQLException {
        clients = clientService.GetAllClient();
        pageSize = 5; // Nombre de clients par page
        currentPage = 1; // Page actuelle
        calculateTotalPages();
    }


    private void calculateTotalPages() {
        totalPages = (int) Math.ceil((double) clients.size() / pageSize);
    }
    public void updateClient(Client client)throws SQLException {
        clientService.updateClient(client); // Appel à la méthode de mise à jour dans le service
        // Mettre à jour la liste des clients pour refléter les modifications
        clients = clientService.GetAllClient();

    }

    public String createClient() throws SQLException {
        Client newClient = new Client();
        newClient.setFirstname((String) firstNameInput.getValue());
        newClient.setLastname((String) lastNameInput.getValue());
        newClient.setEmail((String) emailInput.getValue());
        newClient.setPhone((String) phoneInput.getValue());
        newClient.setPassword((String) passwordInput.getValue());

        boolean success = clientService.createClient(newClient);

        if (success) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Client added successfully."));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add client."));
        }

        // Redirection vers la page contenant le tableau mis à jour
        return "ListClients?faces-redirect=true";
    }

    public void saveChanges(Client client1) throws SQLException {
        System.out.println(client1.getEmail());
    }


    public void deleteClient(int id) throws SQLException {
        clientService.deleteClient(id);
    }

    public List<Client> getClients(){
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, clients.size());
        return clients.subList(startIndex, endIndex);
    }
    public int getPageSize() {
        return pageSize;
    }
    public void previousPage() {
        if (currentPage > 1) {
            editablenext = true;
            currentPage--;
        }
        if (currentPage==1){
            editablenext = true;
            editableprevious = false;
        }
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            editableprevious=true;
            editablenext = true;
            currentPage++;
        }
        if(currentPage==totalPages){
            editablenext = false;
        }
    }
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) clients.size() / pageSize);
    }

    public Client getClient(){
        return client;
    }

    public UIInput getFirstNameInput() {
        return firstNameInput;
    }

    public void setFirstNameInput(UIInput firstNameInput) {
        this.firstNameInput = firstNameInput;
    }

    public UIInput getLastNameInput() {
        return lastNameInput;
    }

    public void setLastNameInput(UIInput lastNameInput) {
        this.lastNameInput = lastNameInput;
    }

    public UIInput getEmailInput() {
        return emailInput;
    }

    public void setEmailInput(UIInput emailInput) {
        this.emailInput = emailInput;
    }

    public UIInput getPhoneInput() {
        return phoneInput;
    }

    public void setPhoneInput(UIInput phoneInput) {
        this.phoneInput = phoneInput;
    }

    public UIInput getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(UIInput passwordInput) {
        this.passwordInput = passwordInput;
    }

    public UIInput getFirstName1Input() {
        return firstName1Input;
    }

    public void setFirstName1Input(UIInput firstName1Input) {
        this.firstName1Input = firstName1Input;
    }







    public void setFirstNameUInput(String firstNameUInput) {
        this.firstNameUInput = firstNameUInput;
    }

    public String getLastNameUInput() {
        return lastNameUInput;
    }

    public void setLastNameUInput(String lastNameUInput) {
        this.lastNameUInput = lastNameUInput;
    }

    public String getEmailUInput() {
        return emailUInput;
    }

    public void setEmailUInput(String emailUInput) {
        this.emailUInput = emailUInput;
    }

    public String getPhoneUInput() {
        return phoneUInput;
    }

    public void setPhoneUInput(String phoneUInput) {
        this.phoneUInput = phoneUInput;
    }

    public String getPasswordUInput() {
        return passwordUInput;
    }

    public void setPasswordUInput(String passwordUInput) {
        this.passwordUInput = passwordUInput;
    }

    public int getInputid() {
        return inputid;
    }

    public void setInputid(int inputid) {
        this.inputid = inputid;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    public void toggleInputs(Client client) {
        client.toggleInputs();
    }

    public boolean isEditableprevious() {
        return editableprevious;
    }

    public void setEditableprevious(boolean editableprevious) {
        this.editableprevious = editableprevious;
    }

    public boolean isEditablenext() {
        return editablenext;
    }

    public void setEditablenext(boolean editablenext) {
        this.editablenext = editablenext;
    }
}