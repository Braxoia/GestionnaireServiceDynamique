package utilisateurs;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Programmeur extends Utilisateur{
    private URLClassLoader FTPServer;

    public Programmeur(String login, String password, URLClassLoader FTPServer) {
        super(login, password);
        this.FTPServer = FTPServer;
    }

    public URLClassLoader getFTPServer() {
        return FTPServer;
    }

    public void setFTPServer(URLClassLoader FTPServer) {
        this.FTPServer = FTPServer;
    }

    public void nettoyageFTPServer() {
        /*
                Ici afin de d�charger la classe supprim� on proc�de en 4 �tapes:
                    1- On r�cup�re le tableau d'URL (o� l'url FTP est contenu)
                    2- On met � null notre URLClassLoader afin que notre System.gc s'en d�barasse et d�charge donc les classes load�es
                    (il faut absoluement au pr�lable que la classe supprim�e ou mis � jour n'est plus aucun lien : ni avec l'URLClassLoader
                    ni avec ServiceRegistry)
                    3- On r�cr�� une instance d'URLClassLoader afin de pouvoir loader � nouveau des classes, les classes charg�es pr�cedemment sont
                    toujours existantes puisqu'ils ont un lien avec ServiceRegistry.
        */
        URL[] urls = this.FTPServer.getURLs();
        this.FTPServer = null;
        System.gc();
        this.FTPServer = URLClassLoader.newInstance(urls);
    }
}
