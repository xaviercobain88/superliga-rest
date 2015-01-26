package security.domain.model;

import core.domain.model.User;
import org.apache.commons.codec.binary.Base64;
import utils.exception.InvalidArgumentException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xavier on 1/23/15.
 */
@Entity
public class DistributedServiceToken {

    public static final Integer expireMinutes=5;

    @Id
    protected  String token;
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;
    protected Timestamp expireTime;



    public DistributedServiceToken(User user) throws InvalidArgumentException {
        if(user==null || user.getId()==null){
            throw  new InvalidArgumentException("User is invalid");
        }
        this.user = user;
        Date now = new Date();
        expireTime= new Timestamp(now.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(expireTime.getTime());
        cal.add(Calendar.MINUTE, expireMinutes);
        expireTime = new Timestamp(cal.getTime().getTime());
        String hash = user.getId().toString()+expireTime.toString();
        byte [] tokenByte =  Base64.encodeBase64(hash.getBytes());
        token= new String(tokenByte);

    }

    public DistributedServiceToken() {
    }

    public boolean isExpired(){
        Date now = new Date();
        Timestamp nowTimestamp = new Timestamp(now.getTime());
        if(expireTime!=null){
            return expireTime.before(nowTimestamp);
        }
        return false;

    }

    public String getToken() {
        return token;
    }
}
