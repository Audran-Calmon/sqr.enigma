package enigma;

public class Machine {

	private Rotor leftRotor;
	private Rotor middleRotor;
	private Rotor rightRotor;
	private Reflector reflector;

	public void initRotors(Reflector reflector, Rotor left, Rotor middle, Rotor right) {    // Cette fonction initialise les  3 rotor de la machine
		this.reflector = reflector;                                                     // ainsi que le reflector
		leftRotor = left;                                                               //
		middleRotor = middle;                                                           //
		rightRotor = right;                                                             //
	}

	public void setPositions(String setting) {                              //Cette fonction défini la position initiale de chaque rotor
		char[] charSettings = setting.toCharArray();                    //
		reflector.setPosition(Rotor.toIndex(charSettings[0]));          //
		leftRotor.setPosition(Rotor.toIndex(charSettings[1]));          //
		middleRotor.setPosition(Rotor.toIndex(charSettings[2]));        //
		rightRotor.setPosition(Rotor.toIndex(charSettings[3]));         //
	}
	
	public void configure(Reflector reflector, Rotor left, Rotor middle, Rotor right, String setting) { //C'est la fonctione appelée depuis le main
		this.initRotors(reflector, left, middle, right);                                            //Elle appelle les fonctions d'initialisation des rotors et des positions
		this.setPositions(setting);

	}

	public String convert(String msg) {                                     //Ceci est la fonction qui code le message
		msg = msg.toUpperCase();                                        //celle ligne met tous les caractères du message en majuscules
                
		char[] msgChars = msg.toCharArray();                            //Ces 5 prochaines lignes Enregistrent le message en enlavant les espaces
		String result = "";                                             //
		for (char c : msgChars) {                                       //
			result += convertChar(c);                               //
		}                                                               //
		return result;                                                  //Puis cette ligne renvoie le message en majuscule sans espaces.
	}

	char convertChar(char c) {
		advanceRotors();
		int charIndex = Rotor.toIndex(c);
		int output;
		output = rightRotor.convertForward(charIndex);
		output = middleRotor.convertForward(output);
		output = leftRotor.convertForward(output);
		output = reflector.convertForward(output);
		output = leftRotor.convertBackward(output);
		output = middleRotor.convertBackward(output);
		output = rightRotor.convertBackward(output);
		return Rotor.toLetter(output);

	}

	void advanceRotors() {
		boolean advanceLeft = false;
		boolean advanceMiddle = false;
		boolean advanceRight = true;
		if (leftRotor.atNotch()) {
		}
		if (middleRotor.atNotch()) {
			advanceMiddle = true;
			advanceLeft = true;
		}
		if (rightRotor.atNotch()) {
			advanceMiddle = true;
			advanceRight = true;
		}
		if (advanceLeft) {
			leftRotor.advance();
		}
		if (advanceRight) {
			rightRotor.advance();
		}
		if (advanceMiddle) {
			middleRotor.advance();
		}
	}
}
