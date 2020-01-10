package enigma;

/** Class that represents a reflector in the enigma.
 *  @author
 */
public class Reflector extends Rotor {
	
	int[] reflection;
	
	public static Reflector reflectorFactory(String str){
		char[] s = str.trim().replace(" ", "").toCharArray();           //Cette ligne enlève les espaces entre les mots du message
		int[] cipher = new int[26];                                     //création du tableau d'entiers cipher
		for (int i = 0; i< 26; i++){                                    //
			cipher[i] = toIndex(s[i]);                              //
		}
		return new Reflector(cipher);                                   //renvoie de cipher
	}
	
	Reflector(int[] r){
		super(r,0);
		reflection = r;
	}
        
    public int convertForward(int p) {                                          //cette fonction convertie je ne sais quoi
        return ((reflection[((p)%26+26)%26])%26+26)%26;
    }

    @Override
    public int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void advance() {
    }

}
