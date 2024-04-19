package elements;

import java.util.ArrayList;

public class QuestionsDef {

    public static ArrayList<Question> getQuestions(){

        //todo opcional agregar un helper para la db que nos permite manejar estos datos

        ArrayList<Question> q=new ArrayList<Question>();

        ArrayList<Response> r1=new ArrayList<Response>();
        r1.add(new Response("Con ninguno",1));
        r1.add(new Response("Con uno a tres amigos",-0.5));
        r1.add(new Response("Con cuatro o más",-1));
        Question p1=new Question("¿Con cuántos amigos compartes tus problemas?",r1,1);

        ArrayList<Response> r2=new ArrayList<Response>();
        r2.add(new Response("Ninguno",1));
        r2.add(new Response("De 1 a 3",-0.5));
        r2.add(new Response("Cuatro o más",-1));
        Question p2=new Question("¿Cuántos abrazos o besos recibes por semana?",r2,2);

        q.add(p1);
        q.add(p2);

        return q;

    }
}
