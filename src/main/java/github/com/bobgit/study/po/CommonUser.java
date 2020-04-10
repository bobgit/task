package github.com.bobgit.study.po;


import github.com.bobgit.study.jsonResponse.BaseJsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "常规用户信息")
public class CommonUser extends BaseJsonObject {
    @ApiModelProperty(value = "客户端标识。1-患者端，2-医生端，3-药师端")
    private Long typeId;
    @ApiModelProperty(value = "用户ID,关联t_doctor.id，t_patient.id,t_pharmacy.id")
    private Long userId;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "登陆密码")
    private String passHash;
    @ApiModelProperty(value = "姓名")
    private String trueName;
    @ApiModelProperty(value = "使用类型:0 表示注册,1 表示已经使用")
    private String useType;
    @ApiModelProperty(value = "验证码")
    private String verifyCode;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
