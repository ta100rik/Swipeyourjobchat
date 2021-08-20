package com.swipeyourjob.swipeyourjobchat.Service;

import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl.ChatDaoImpl;
import com.swipeyourjob.swipeyourjobchat.Domain.WebUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthenticationService {
    private static final String JWT_SECRET = "79B4BD6CC80E212A8EDB52FBB7BE427891CA622075581961774EA48046AB0FAC4495022F9BBAE2167CAA19D060AE21ABA1B1FA1E4755061A0960C9F2AE037A34=";
    private static final ChatDaoImpl chatDao = new ChatDaoImpl();
    @SuppressWarnings("deprecation")
    private Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        try{

            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
    public WebUser getWebuserByUserid(int userid){
        return chatDao.getUserById(userid);
    }
    public int getUserid(String jwt){
        Claims claim = decodeJWT(jwt);
        if(claim == null){
            return 0;
        }else{
            int role = (int) claim.get("userid");
            return role;
        }

    }
}
