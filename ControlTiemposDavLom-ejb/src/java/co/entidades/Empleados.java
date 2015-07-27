/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 908034
 */
@Entity
@Table(name = "EMPLEADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEE")
    private BigInteger employee;
    @Size(max = 255)
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Size(max = 255)
    @Column(name = "EMPLOYEE_DEPUTE_COUNTRY")
    private String employeeDeputeCountry;
    @Size(max = 255)
    @Column(name = "EMPLOYEE_BASE_COUNTRY")
    private String employeeBaseCountry;
    @Size(max = 255)
    @Column(name = "IOU")
    private String iou;
    @Size(max = 255)
    @Column(name = "SUB_IOU")
    private String subIou;
    @Size(max = 255)
    @Column(name = "CUSTOMER")
    private String customer;
    @Size(max = 255)
    @Column(name = "GROUP_CUSTOMER")
    private String groupCustomer;
    @Size(max = 255)
    @Column(name = "SUB_GROUP_CUSTOMER")
    private String subGroupCustomer;
    @Column(name = "AM_#")
    private BigInteger am;
    @Size(max = 255)
    @Column(name = "AM")
    private String am1;
    @Column(name = "PROJECT_#")
    private BigInteger project;
    @Size(max = 255)
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "DATE_OF_JOINING")
    private BigInteger dateOfJoining;
    @Size(max = 255)
    @Column(name = "DESIGNATION")
    private String designation;
    @Size(max = 255)
    @Column(name = "GRADE")
    private String grade;
    @Size(max = 255)
    @Column(name = "TEAM_ROLE")
    private String teamRole;
    @Size(max = 255)
    @Column(name = "PERSON_TYPE")
    private String personType;
    @Column(name = "ALLOCATION_START_DATE")
    private BigInteger allocationStartDate;
    @Column(name = "DATE_OF_BIRTH")
    private BigInteger dateOfBirth;
    @Column(name = "AGE")
    private BigInteger age;
    @Size(max = 255)
    @Column(name = "MALE_FEMALE")
    private String maleFemale;
    @Size(max = 255)
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;
    @Column(name = "TCS_EXPERIENCE_YRS_")
    private BigInteger tcsExperienceYrs;
    @Column(name = "TOTAL_EXPERIENCE_YRS_")
    private BigInteger totalExperienceYrs;
    @Size(max = 255)
    @Column(name = "NATIONALITY")
    private String nationality;
    @Size(max = 255)
    @Column(name = "ASSIGNMENT_STATUS")
    private String assignmentStatus;
    @Size(max = 20)
    @Column(name = "EMPLOYEE_GROUP")
    private String employeeGroup;
    @Transient
    private String administrador;

    public Empleados() {
    }

    public Empleados(BigInteger employee) {
        this.employee = employee;
    }

    public BigInteger getEmployee() {
        return employee;
    }

    public void setEmployee(BigInteger employee) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDeputeCountry() {
        return employeeDeputeCountry;
    }

    public void setEmployeeDeputeCountry(String employeeDeputeCountry) {
        this.employeeDeputeCountry = employeeDeputeCountry;
    }

    public String getEmployeeBaseCountry() {
        return employeeBaseCountry;
    }

    public void setEmployeeBaseCountry(String employeeBaseCountry) {
        this.employeeBaseCountry = employeeBaseCountry;
    }

    public String getIou() {
        return iou;
    }

    public void setIou(String iou) {
        this.iou = iou;
    }

    public String getSubIou() {
        return subIou;
    }

    public void setSubIou(String subIou) {
        this.subIou = subIou;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getGroupCustomer() {
        return groupCustomer;
    }

    public void setGroupCustomer(String groupCustomer) {
        this.groupCustomer = groupCustomer;
    }

    public String getSubGroupCustomer() {
        return subGroupCustomer;
    }

    public void setSubGroupCustomer(String subGroupCustomer) {
        this.subGroupCustomer = subGroupCustomer;
    }

    public BigInteger getAm() {
        return am;
    }

    public void setAm(BigInteger am) {
        this.am = am;
    }

    public String getAm1() {
        return am1;
    }

    public void setAm1(String am1) {
        this.am1 = am1;
    }

    public BigInteger getProject() {
        return project;
    }

    public void setProject(BigInteger project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigInteger getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(BigInteger dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public BigInteger getAllocationStartDate() {
        return allocationStartDate;
    }

    public void setAllocationStartDate(BigInteger allocationStartDate) {
        this.allocationStartDate = allocationStartDate;
    }

    public BigInteger getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(BigInteger dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getMaleFemale() {
        return maleFemale;
    }

    public void setMaleFemale(String maleFemale) {
        this.maleFemale = maleFemale;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public BigInteger getTcsExperienceYrs() {
        return tcsExperienceYrs;
    }

    public void setTcsExperienceYrs(BigInteger tcsExperienceYrs) {
        this.tcsExperienceYrs = tcsExperienceYrs;
    }

    public BigInteger getTotalExperienceYrs() {
        return totalExperienceYrs;
    }

    public void setTotalExperienceYrs(BigInteger totalExperienceYrs) {
        this.totalExperienceYrs = totalExperienceYrs;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    /*
     * @XmlTransient public List<RegistroHoras> getRegistroHorasList() { return
     * registroHorasList; }
     *
     * public void setRegistroHorasList(List<RegistroHoras> registroHorasList) {
     * this.registroHorasList = registroHorasList; }
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employee != null ? employee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.employee == null && other.employee != null) || (this.employee != null && !this.employee.equals(other.employee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.entidades.Empleados[ employee=" + employee + " ]";
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
