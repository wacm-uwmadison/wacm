// ------------------------------------------------------------------------------------------------
//																																class Data
// ------------------------------------------------------------------------------------------------
public class Data
{
	static String button[][];
	static String help[][];
		
	Data() {
		button = new String[27][3];
		button[0][0] = "Please enter the code here:";	button[0][1] = "Bitte hier das Programm eingeben"; button[0][2] = "Entrez le programme ici:";
		
		button[1][0] = "draw";						button[1][1] = "zeichnen";		button[1][2] = "dessiner";
		button[2][0] = "line by line";		button[2][1] = "zeilenweise";	button[2][2] = "chaque ligne";
		button[3][0] = "next step";				button[3][1] = "nächste Zeile";	button[3][2] = "prochaine ligne";
		button[4][0] = "rest at once";		button[4][1] = "den Rest";		button[4][2] = "reste";
		button[5][0] = "Logo syntax";			button[5][1] = "Logo Syntax";	button[5][2] = "la syntaxe de Logo";		
		button[6][0] = "help";						button[6][1] = "Hilfe";				button[6][2] = "aide";
		button[7][0] = "upload";					button[7][1] = "hochladen";		button[7][2] = "charger dans le web";
		// upload window
		button[10][0] = "Please give a short description of your procedure:";					button[10][1] = "Bitte gib eine kurze Beschreibung Deiner Prozedur:";								button[10][2] = "S'il-t-plait donne une description courte de ta procédure:";
		button[11][0] = "test the procedure";																			button[11][1] = "Prozedur testen";														button[11][2] = "tester la procédure";
		button[12][0] = "upload the procedure";																		button[12][1] = "Prozedur hochladen";													button[12][2] = "charger dans le web";
		button[13][0] = "Upload a procedure";																			button[13][1] = "Eine Prozedur hochladen";												button[13][2] = "Charger une procédure sur notre page web";
		button[14][0] = "Name of the procedure:";																	button[14][1] = "Der Name der Prozedur:";													button[14][2] = "Le nom de la procédure:";
		button[15][0] = "Test variables( [var]=[number],... )";																		button[15][1] = "Variablentestwerte( [var]=[zahl],... )";													button[15][2] = "variables test( [var]=[nombre],... )";		
		//button[16][0] = "test value for var2:";																		button[16][1] = "Testwert für var2";													button[16][2] = "valeur test pour var2";
		button[17][0] = "Your name:";																							button[17][1] = "Dein Name:";															button[17][2] = "ton nom:";
		button[18][0] = "A procedure must be tested before it can be uploaded!";					button[18][1] = "Eine Prozedur muß vor dem Hochladen getestet werden!";								button[18][2] = "  Une procédure doit être testée avant de la charger sur la page!";
		button[19][0] = "The procedure is ok, it can't be changed while this dialog is opened!";		button[19][1] = "Die Prozedur ist ok, sie kann nicht mehr verändert werden solange dieses Fenster offen ist";	button[19][2] = "  la procédure fonctionne, elle ne peut plus être changée si cette fenêtre reste ouverte!";
		button[20][0] = "The procedure contains an error, please modify the code and try again!";		button[20][1] = "Die Prozedur ist Fehlerhaft, bitte überarbeiten!";								button[20][2] = "  La procédure contient une faute, il faut la reviser";
		button[21][0] = "The procedure has been succesfully uploaded!";						button[21][1] = "Die Prozedur ist hochgeladen worden!";										button[21][2] = "  La procédure a été chargée sur notre cite internet";
		button[22][0] = "An error occured while uploading the procedure! Your direct connection to the internet might be interrupted.";						button[22][1] = "Während des Hochladens trat ein Fehler auf";									button[22][2] = "  La procédure n'a pas pu être chargé à cause d'une faute de connection!";
		//proxy
		button[23][0] = "There is no direct connection from your computer to the internet. If you use a proxy server please indicate it's name and it's port. If you don't use a proxy server please verify your connection to the internet.";		button[23][1] = "Il n'y pas de connection directe entre ton ordinateur et l'internet. Si tu utilises un serveur proxy s'il te plait indique son nom et son port. Si tu n'utilises pas de serveur proxy il faut vérifier la connection internet.";		button[23][2] = "Es besteht keine direkte Verbindung zwischen Deinem Computer und dem Internet. Wenn Du einen Proxyserver benutzt gibt bitte seinen Namen und seinen Port an.";
		button[24][0] = "Name";						button[24][1] = "Nom";									button[24][2] = "Name";
		button[25][0] = "Port";						button[25][1] = "Port";									button[25][2] = "Port";
		button[26][0] = "No proxy";						button[26][1] = "Pas de proxy";									button[26][2] = "Kein Proxy";



		help = new String[2][3];
		help[0][0] ="SYNTAX :\n\n" +
								"color [colorname]\n     changes the color with which Logo draws\n     colorname can be:\n" +
								"     black, white, red, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, yellow\n" +
								"down\n     see up.\n" +
								"end\n     end of a procedure\n" +
								"go [exp]\n     makes Logo go forward be [exp] steps.\n" +
								"goto [name], [var]=[exp], [var]=[exp], ...\n     go to the procedure [name]. You can add as much [var]=[exp] as you want (none is also possible).\n     [var] is the name of a variable that will appear in the procedure [name] with the value [exp]\n" +
								"left [exp]\n     like right but turns to the left side. Can be written as \"lt\".\n" +
								"newvar [var] = [exp]\n     creates a nes variable with the name [var] that has the value [exp].\n" +
								"procedure [procedurename]\n     here starts a new procedure\n" +
								"repeat [times], [name],	 [var]=[exp], ...\n     go to the procedure [name] [times] times. The number of the round will be available in\n     the procedure [name] as a variable called \"loop\".\n     [var]=[exp] means the same as for the goto command above.\n" +
								"right [exp]\n     makes Logo turn right the value of [exp]. One cercle corresponds to 360.\n" +
								"     right can be written \"rt \" too.\n" +
								"up and down\n     with up and down you can make Logo lift his pencil so he can walk without making lines\n" +
								"[var] = [exp]\n     associates the value of [exp] to the variable [var]\n" +
								
								"\nGeneral information:\n" +
								"\"/\" is used for comments = text that Logo does not read\n" +
								"\".\" for decimals\n\n" +
								"If you have to give a number you can write something like (var1*2*(56+66))\n" +
								"this means that you can use mathematical expressions instead of numbers\n" +
								"that's why I wrote [exp] where you have to give a number.";
								
		help[1][0] ="Interpreter functions (what all the buttons and menus do):\n\n" +
								"\nbutton \"draw\"\n     this will make Logo draw the image according to the commands you have written\n" +
								"\nbutton \"line by line\"\n     this will make Logo draw like the button draw does but Logo will stop at each\n" +
								"     line and wait until you say him to draw the next one\n" +
								"\nbutton \"next step\"\n     you can only use this button when you have clicked on line by line. It will tell Logo to draw the next line\n" +
								"\nbutton \"rest at once\"\n     when you are doing line by line this will make draw all the rest at once without asking any more\n" +
								"\nbutton \"stop\"\n     this will make Logo stop drawing at any time. It works with draw and line by line.\n" +
								"\nbutton \"upload\"\n     a window that allows you to send procedures on our webpage will appear\n" +
								"\nbutton \"Logo syntax\"\n     a short documentation of the Logo language\n" +
								"\nbutton \"help\"\n     this window will appear\n" +	
								"";
								
		help[0][1] ="SYNTAX :\n\n" +
								"prozedur [prozedurname]\n     hier geht eine neue Prozedur loss. Bei [prozedurname] steht ihr name.\n" +
								"ende\n     hier endet eine Prozedur\n" +
								"gehezu [name], [var]=[exp], [var]=[exp]...\n     so wird eine Prozedur mit dem Namen [name] aufgerufen. Du kannst so viele [var]=[exp]\n     zufügen wie Du willst (aber auch gar keines).     [var] ist der Name einer Variablen,\n     die mit dem Wert [exp] in der Prozedur [name] erscheinen wird\n" +
								"wiederhole [runden], [name], [var]=[exp], [var]=[exp]...\n     gehe zu Prozedur [name] und zeichne sie [runden] mal. Das wievielte mal sie\n     gerade gezeichnet wird kann man durch die Variable \"loop\" in der Prozedur [name] abfragen\n     \"wh\" ist eine Abkürzung für wiederhole\n" +
								"farbe [farbname]\n     andert die Farbe mit der Logo malt\n     [farbname] kan sein:\n" +
								"     schwarz, weiss, rot, blau, grau, grün, orange, gelb\n" +
								"[var] = [exp]\n     gibt der Variablen [var] den Wert [exp]\n" +
								"neuevar [var] = [exp]\n     erstellt eine neu Variable mit dem Namen [var] die den Wert [exp] hat.\n" +
								"rechts [winkel]\n     so dreht Ihr Logo nach rechts. [winkel] ist in Grad angegeben (also 360 = ein Kreis)\n" +
								"     statt rechts kann man auch \"re \" schreiben\n" +
								"links [winkel]\n     geht wie rechts aber lasst Logo nach links drehen. Ihr konnt auch \"li\" schreiben.\n" +
								"vorwarts [schritte]\n    so geht Logo vorwarts un [schritte] Schritte.\n" +
								"hoch und runter bzw. stifthoch und stifrunter\n     so hebt Logo seinen Stift hoch und lauft ohne zu malen\n" +
								"\nAllgemeine Informationen:\n" +
								"\"/\" sagt Logo dass er diese Zeile nicht ausfuren soll\n" +
								"\".\" der Punkt! gibt Nachkommastellen an\n" +
								"uberall wo Ihr eine Zahl angeben musst konnt Ihr auch eine Rechnung wie (var1*2*(56+66)) angeben.\n" +
								"deswegen habe ich auch oft [exp] geschrieben wo eine Zahl stehen muss.";


		help[1][1] ="Interpreter Funktionen (was all die Knöpfe machen):\n\n" +
								"\nButton \"zeichnen\"\n     Logo wird alles malen was Ihr ihm in den Text geschrieben habt.\n" +
								"\nButton \"zeilenweise\"\n     Logo wird eine Zeile malen und dann immer warten bis Ihr Ihm sagt weiterzumachen.\n" +
								"\nButton \"nachste Zeile\"\n    wenn Ihr zeilenweise gewahlt habt wird dies Logo sagen, dass er die nachste Zeile malen soll.\n" +
								"\nButton \"den Rest\"\n     wenn Ihr zeilenweise gewahlt habt konnt Ihr hier den Rest malen ohne weiter auf nachte Zeile clicken zu mussen.\n" +
								"\nButton \"stop\"\n     Logo wird aufhören zu malen, egal ob Ihr zeilenweise gewahlt habt oder nicht.\n" +
								"\nButton \"hochladen\"\n     es wird ein Fenster amngezeigt, daß Euch erlaubt ein Prozedur auf unsere WWW Seite hochzuladen\n" +
								"\nButton \"Logo Syntax\"\n     eine kurze Dokumentation der Logo-Sprache\n" +
								"\nButton \"Hilfe\"\n     Dieses Fenster wird angezeigt\n" +
								"";

		help[0][2] ="SYNTAXE :\n\n" +
								"procedure [procedurenom]\n     debut d'une nouvelle procedure avec le nom [procedurenom]\n" +
								"fin\n     fin d'une procedure\n" +
								"vavers [nom], [var]=[exp], [var]=[exp], ...\n     appelle une procedure avec le nom [nom]. Tu peux ajouter autant de [var]=[exp]\n     que tu veux (mais aussi aucun). [var] est le nom d'une variable qui apparaitra\n     dans la procedure [nom] avec la valeur [exp].\n" +
								"repeter [tours], [nom], [var]=[exp], [var]=[exp], ...\n     dessine [tours] fois la procedure [nom]. Dans la procedure [nom]\n     le nombre du tour actuel sera disponible dans la variable \"loop\".\n     [var]=[exp] fonctionne comme pour la commande vavers.\n" +
								"color [nomdecouleur]\n     change la couleur avec la quelle Logo dessine\n     [nomdecouleur] peut etre:\n" +
								"     noir, blanc, rouge, bleu, gris, vert, orange, jaune\n" +
								"[var] = [exp]\n     donne a la variable [var] la valeur [exp]\n" +
								"droite [angle]\n     fait tourner Logo vers la droite d'un angle [angle]. Un cercle represente 360\n" +
								"     au lieu de droite on peut ecrire \"dr \"\n" +
								"gauche [angle]\n     fait Logo tourner a gauche. Cela fonctionne comme droite. On peut ecrire aussi\"ga\".\n" +
								"va [points]\n    Logo va avancer de [points].\n" +
								"marcher ou dessiner\n     Logo va marcher sans laisser un trait.\n" +
								"\nD'autres informations:\n" +
								"\"/\" marque les commentaires\n" +
								"\".\" les decimales sont donnees a l'aide d'un point\n" +
								"partout ou il faut donner un nombre on peut donner un calcul comme (var1*2*(56+66)).\n";

		help[1][2] ="Les fonctions de l'interpreteur(explication des butons):\n\n" +
								"\nButton \"dessiner\"\n     Logo va dessiner tout ce que vous lui avez ecrit.\n" +
								"\nButton \"ligne par ligne\"\n     Logo va dessiner ce qui est ecrit dans une ligne et puis attendre jusqu'a ce que vous lui dites de continuer.\n" +
								"\nButton \"prochaine ligne\"\n    Si vous etes en ligne par ligne, ce buton provoque que Logo dessine la prochaine ligne\n" +
								"\nButton \"le reste\"\n     Si vous etes en ligne par ligne ce buton dit Logo de dessiner tout le reste sans attendre a chaque ligne.\n" +
								"\nButton \"stop\"\n     Logo va arreter de dessiner n'importe si vous etes en ligne par ligne ou pas.\n" +
								"\nbutton \"charger dans le web\"\n     Donne une fenêtre permettant de charger unr procedure sur notre page internet\n" +
								"\nbutton \"la syntaxe de Logo\"\n     Une documentation rapide de la syntaxe du language Logo\n" +
								"\nbutton \"aide\"\n     Cette fenêtre sera affichée\n" +
								"";
	}
}