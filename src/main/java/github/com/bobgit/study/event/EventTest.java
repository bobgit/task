package github.com.bobgit.study.event;

import org.springframework.context.ApplicationEvent;

//Spring事件机制
public class EventTest extends ApplicationEvent {
    private static final long serialVersionUID = 2820601967730434769L;
    //    如下图：Preferences -> Inspections -> Serialization issues -> Serialization class without 'serialVersionUID' 打上勾
//    将光标放到类名上，按atl＋enter键，就会提示生成serialVersionUID了
//简单来说，Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。(InvalidCastException)
//    private static final long serialVersionUID = 3925611069295097367L;
    public EventTest(Object source) {
        super(source);
    }

    public EventTest(Object source,Long typeId,Long userId,String phoneNumber){
        super(source);
        this.typeId = typeId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    private Long typeId;
    private Long userId;
    private String phoneNumber;
    private String passHash;
    private String trueName;
    private String useType;
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
