package ferchulobo777.um.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    //Metodo para crear un token por medio de la Autenticacion
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentTime = new Date();
        Date expirationToken = new Date(currentTime.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);


        //Linea para generar el token
        String token = Jwts.builder()//Contruimos un token JWT llamado token
                .setSubject(username)//aca establecemos el nombre de usuario que esta iniciando sesion
                .setIssuedAt(new Date())//Establecemos la fecha de emision ddel token en momento actual
                .setExpiration(expirationToken)//Establecemos la fecha de caducidad del token
                .signWith(SignatureAlgorithm.HS512,ConstantesSeguridad.JWT_FIRMA)/*Utilizamos este metodo para firmar
                nuestro token y de esta manera evitar la manipulacion o modificacion de este*/
                .compact();//Este metodo finaliza la construccion del token y lo convierte en una caedna compacta
        return token;
    }

    //Metodo para extraer un Username a partir de un token
    public String obtainUsernameOfJwt(String token){
        Claims claims = Jwts.parser()//El método parse se utiliza con el fin de analizar el token
                .setSigningKey(ConstantesSeguridad.JWT_FIRMA)//Establece la clave de firma, que se utiliza para verificar la firma del token
                .parseClaimsJws(token)//Se utiliza para verificar la firma del token, a partir del String "token"
                .getBody();/*Obtenemos el claims(cuerpo) ya verificado del token el cual contendra la informacion de
                nombre de usuario, fecha de expiracion y firma del token*/
        return claims.getSubject();//Devolvemos el nombre de usuario
    }

    // Metodo para validar el token
    public  Boolean validToken(String token){
        try {
            //Validacion del token por medio de la firma que contiene el String token(token)
          Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_FIRMA).parseClaimsJws(token);
          return true;
        } catch (Exception e) {
            throw  new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto")
        }
    }
}
