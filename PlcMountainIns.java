package com.scon.WebInsOZK.entities.policies;

import com.scon.WebIns.OraIns.cmd.base.CmdData;
import com.scon.WebIns.OraIns.cmd.base.OraInsCmdAbstract;
import com.scon.WebIns.entities.customers.base.CustomerBase;
import com.scon.WebIns.entities.noms.NomInsPolicy111InsKind;
import com.scon.WebIns.entities.noms.NomInsPolicy111InsSum;
import com.scon.WebIns.entities.noms.NomInsPolicyType;
import com.scon.WebIns.entities.noms.NomInspolicyinstype;
import com.scon.WebIns.entities.policies.base.PlcBase;
import com.scon.WebIns.sys.def;
import java.io.Serializable;
import java.math.BigDecimal;

public class PlcMountainIns extends PlcBase<PlcMountainInsRow> implements Serializable {

  private static final long serialVersionUID = 1L;
  // тия пропертита съответстват на MemArray2 при load / MemArray3 при save

  private Integer insuranceId;                          // ID на застраховката
  private Integer idZastrahovasht;                      // ИД Застраховащ
  private CustomerBase custZastrahovasht;               // Застраховащ
  private BigDecimal currRate;                          // валутен курс
  //
  private NomInsPolicy111InsKind nomInsKind;            // Вид застраховка
  private NomInsPolicy111InsSum nomInsSum;              // Застрахователна сума за едно лице
  private Integer totalPersonsCount;                    // Общ брой лица
  private BigDecimal totalInsSum;                       // Обща ЗС
  private BigDecimal personPremium;                     // Премия за 1 лице

  public PlcMountainIns(NomInspolicyinstype typePolica, NomInsPolicyType insPolicyType) {
    super(typePolica, insPolicyType);
    custZastrahovasht = new CustomerBase(def.CustRole_Zastrahovasht);
  }

  @Override
  public PlcMountainInsRow NewRowObject() {
    PlcMountainInsRow row = new PlcMountainInsRow();
    row.setPlcObj(this);
    return (row);
  }

  @Override
  public void addCustFromCmd(CustomerBase cust, OraInsCmdAbstract cmd) {
    if (cust.getRole().equals(def.CustRole_Zastrahovasht)) {
      this.setCustZastrahovasht(cust);
    } else {
      super.addCustFromCmd(cust, cmd);
    }
  }

  @Override
  public void FillPlcCodVal(String cCodVal) {
    super.FillPlcCodVal(cCodVal);
  }

  @Override
  public CmdData ConvertToCmd() {
    super.ConvertToCmd();
    this.WriteInteger(this.insuranceId);                         // 1
    this.WriteInteger(this.idZastrahovasht);                     // 2
    this.WriteInteger(this.totalPersonsCount);                   // 3
    this.WriteMoney(this.getPolicyVal(), this.totalInsSum);      // 4
    this.WriteMoney(this.getPolicyVal(), this.getPremTarif());   // 5
    this.WriteNom(this.nomInsKind);                              // 6
    this.WriteNom(this.nomInsSum);                               // 7
    this.WriteMoney(this.getPolicyVal(), this.personPremium);    // 8

    return this;
  }

  @Override
  public void ConvertFromCmd(String[] aRow, OraInsCmdAbstract cmd) {
    this.setTypePolica(cmd.FindNom(def.plcTypeMountainInsurance, NomInspolicyinstype.class));
    this.insuranceId = this.ReadInteger(aRow[1]);
    this.idZastrahovasht = this.ReadInteger(aRow[2]);
    this.totalPersonsCount = this.ReadInteger(aRow[3]);
    this.totalInsSum = ReadNumber(aRow[5]);
    this.nomInsKind = cmd.FindNom(this.ReadString(aRow[8]), NomInsPolicy111InsKind.class);
    this.nomInsSum = cmd.FindNom(this.ReadString(aRow[9]), NomInsPolicy111InsSum.class);
    this.personPremium = ReadNumber(aRow[11]);
  }

  @Override
  public PlcMountainIns asPlc() {
    PlcMountainIns plc = new PlcMountainIns(this.getTypePolica(), this.getInsPolicyType());
    this.setPlcBaseMembers(plc);
    plc.setInsuranceId(getInsuranceId());
    plc.setIdZastrahovasht(getIdZastrahovasht());
    plc.setCustZastrahovasht(getCustZastrahovasht().asCustomerBase(def.CustRole_Zastrahovasht));
    plc.setTotalInsSum(getTotalInsSum());
    plc.setCurrRate(getCurrRate());
    plc.setNomInsKind(getNomInsKind());
    plc.setNomInsSum(getNomInsSum());
    plc.setTotalPersonsCount(getTotalPersonsCount());
    plc.setPersonPremium(getPersonPremium());
    return plc;
  }

  public Integer getInsuranceId() {
    return insuranceId;
  }

  public void setInsuranceId(Integer insuranceId) {
    this.insuranceId = insuranceId;
  }

  public Integer getIdZastrahovasht() {
    return idZastrahovasht;
  }

  public void setIdZastrahovasht(Integer idZastrahovasht) {
    this.idZastrahovasht = idZastrahovasht;
  }

  public CustomerBase getCustZastrahovasht() {
    return custZastrahovasht;
  }

  public void setCustZastrahovasht(CustomerBase custZastrahovasht) {
    this.custZastrahovasht = custZastrahovasht;
  }

  public BigDecimal getTotalInsSum() {
    return totalInsSum == null ? BigDecimal.ZERO.setScale(2) : totalInsSum;
  }

  public void setTotalInsSum(BigDecimal totalInsSum) {
    this.totalInsSum = totalInsSum;
  }

  public BigDecimal getCurrRate() {
    return currRate == null ? BigDecimal.ZERO.setScale(def.CURR_RATE_SCALE) : currRate;
  }

  public void setCurrRate(BigDecimal currRate) {
    this.currRate = currRate;
  }

  public NomInsPolicy111InsKind getNomInsKind() {
    return nomInsKind;
  }

  public void setNomInsKind(NomInsPolicy111InsKind nomInsKind) {
    this.nomInsKind = nomInsKind;
  }

  public NomInsPolicy111InsSum getNomInsSum() {
    return nomInsSum;
  }

  public void setNomInsSum(NomInsPolicy111InsSum nomInsSum) {
    this.nomInsSum = nomInsSum;
  }

  public Integer getTotalPersonsCount() {
    return totalPersonsCount == null ? 0 : totalPersonsCount;
  }

  public void setTotalPersonsCount(Integer totalPersonsCount) {
    this.totalPersonsCount = totalPersonsCount;
  }

  public BigDecimal getPersonPremium() {
    return personPremium == null ? BigDecimal.ZERO.setScale(2) : personPremium;
  }

  public void setPersonPremium(BigDecimal personPremium) {
    this.personPremium = personPremium;
  }

}
