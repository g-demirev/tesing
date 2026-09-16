package com.scon.WebInsOZK.entities.policies;

import com.scon.WebIns.OraIns.cmd.base.CmdData;
import com.scon.WebIns.OraIns.cmd.base.OraInsCmdAbstract;
import com.scon.WebIns.entities.policies.base.PlcRowBase;
import com.scon.WebIns.sys.Tools;
import java.io.Serializable;
import java.util.Date;

public class PlcMountainInsRow extends PlcRowBase<PlcMountainIns> implements Serializable {

  private static final long serialVersionUID = 1L;
// тия пропертита съответстват на MemArray3 при load / MemArray4 при save
  private String srok;    // срок
  private Date begDate;   // начало дата
  private String begTime; // начало час
  private Date endDate;   // край дата
  private String endTime; // край час
//
  private Integer coverId;                     // Осн. покритие - ИД
  //
  private String personNames;                  // Име(на)
  private Date birthDate;                      // Дата на раждане
  private Date inclDate;                       // Дата на включване
  private Date exclDate;                       // Дата на изключване
  private boolean excluded;                    // Изключен

  @Override
  public CmdData ConvertToCmd() {
    super.ConvertToCmd();
    this.WriteInteger(this.ID_Obj);                                                // 1
    this.WriteString(this.Status);                                                 // 2
    this.WriteInteger(this.IDAnex);                                                // 3
    this.WriteInteger(this.RowNo);                                                 // 4
    this.WriteString(this.srok);                                                   // 5
    this.WriteDate(this.begDate);                                                  // 6
    this.WriteString(this.begTime);                                                // 7
    this.WriteDate(this.endDate);                                                  // 8
    this.WriteString(this.endTime);                                                // 9
    this.WriteMoney(this.plcObj.getPolicyVal(), this.ObjTotPremTrf_Amn);           // 10
    this.WriteMoney(this.plcObj.getPolicyVal(), this.ObjTotPremDisc_Amn);          // 11
    this.WriteMoney(this.plcObj.getPolicyVal(), this.ObjTotPremDue_Amn);           // 12
    this.WriteInteger(this.coverId);                                               // 13
    this.WriteString(this.plcObj.getInsPolicyType() != null ? this.plcObj.getInsPolicyType().getNomId() + "01" : null); // 14
    this.WriteNumber(this.plcObj.getCurrRate());                                   // 15
    this.WriteString("T");                                                         // 16
    this.WriteMoney(this.plcObj.getPolicyVal(), this.plcObj.getPersonPremium());   // 17
    this.WriteMoney(this.plcObj.getPolicyVal(), this.plcObj.getTotalInsSum());     // 18
    this.WriteInteger(this.plcObj.getTotalPersonsCount());                         // 19
    this.WriteString(this.personNames);                                            // 20
    this.WriteDate(this.birthDate);                                                // 21
    this.WriteDate(this.inclDate);                                                 // 22
    this.WriteDate(this.exclDate);                                                 // 23
    this.WriteBoolean(this.excluded);                                              // 24

    return this;
  }

  @Override
  public void ConvertFromCmd(String[] aRow, OraInsCmdAbstract cmd) {
    super.ConvertFromCmd(aRow, cmd);
    this.ID_Obj = this.ReadInteger(aRow[1]);
    this.Status = this.ReadString(aRow[2]);
    this.IDAnex = this.ReadInteger(aRow[3]);
    this.RowNo = this.ReadInteger(aRow[4]);
    this.srok = this.ReadString(aRow[5]);
    this.begDate = this.ReadDate(aRow[6]);
    this.begTime = this.ReadString(aRow[7]);
    this.endDate = this.ReadDate(aRow[8]);
    this.endTime = this.ReadString(aRow[9]);
    this.ObjTotPremTrf_Amn = this.ReadNumber(aRow[11]);
    this.ObjTotPremDisc_Amn = this.ReadNumber(aRow[13]);
    this.ObjTotPremDue_Amn = this.ReadNumber(aRow[15]);
    this.coverId = this.ReadInteger(aRow[16]);
//    this.plcObj.setPersonPremium(this.ReadNumber(aRow[20]));
//    this.plcObj.setTotalInsSum(this.ReadNumber(aRow[22]));
//    this.plcObj.setTotalPersonsCount(this.ReadInteger(aRow[23]));
    this.personNames = this.ReadString(aRow[24]);
    this.birthDate = this.ReadDate(aRow[25]);
    this.inclDate = this.ReadDate(aRow[26]);
    this.exclDate = this.ReadDate(aRow[27]);
    this.excluded = Tools.Str2Bool(this.ReadString(aRow[28]));
  }

  @Override
  public PlcMountainInsRow asPlcRow() {
    PlcMountainInsRow row = new PlcMountainInsRow();
    this.copyObject(row);

    return row;
  }

  public void copyObject(PlcMountainInsRow row) {
    this.setPlcRowBaseMembers(row);
    row.setSrok(getSrok());
    row.setBegDate(getBegDate());
    row.setBegTime(getBegTime());
    row.setEndDate(getEndDate());
    row.setEndTime(getEndTime());
    //
    row.setCoverId(getCoverId());
    //
    row.setBirthDate(getBirthDate());
    row.setExclDate(getExclDate());
    row.setExcluded(isExcluded());
    row.setInclDate(getInclDate());
    row.setPersonNames(getPersonNames());
  }

  public String getSrok() {
    return srok;
  }

  public void setSrok(String srok) {
    this.srok = srok;
  }

  public Date getBegDate() {
    return begDate;
  }

  public void setBegDate(Date begDate) {
    this.begDate = begDate;
  }

  public String getBegTime() {
    return begTime;
  }

  public void setBegTime(String begTime) {
    this.begTime = begTime;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Integer getCoverId() {
    return coverId;
  }

  public void setCoverId(Integer coverId) {
    this.coverId = coverId;
  }

  public String getPersonNames() {
    return personNames;
  }

  public void setPersonNames(String personNames) {
    this.personNames = personNames;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Date getInclDate() {
    return inclDate;
  }

  public void setInclDate(Date inclDate) {
    this.inclDate = inclDate;
  }

  public Date getExclDate() {
    return exclDate;
  }

  public void setExclDate(Date exclDate) {
    this.exclDate = exclDate;
  }

  public boolean isExcluded() {
    return excluded;
  }

  public void setExcluded(boolean excluded) {
    this.excluded = excluded;
  }

}
