package player;

public class PlayerStatus {
	private String nickname;
	public int score;
	public int lives;
	public int health;
	public String weaponInHand;
	public double positionX;
	public double positionY;
	public static String gameName;
	private static final int Knife = 1_000;
	private static final int Sniper = 10_000;
	private static final int Kalashnikov = 20_000;

	//1.
	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}

	public void initPlayer(String nickName, int lives) {
		initPlayer(nickName);
		this.lives = lives;
	}

	public void initPlayer(String nickName, int lives, int score) {
		initPlayer(nickName, lives);
		this.score = score;
	}

	//metoda pentru numarul perfect
	public boolean perfectNumber(int number) {
		int sum = 0;
		for(int i = 1; i <= number/2; i++) {
			if(number % i == 0) {
				sum += i;
			}
		}
		if(sum == number) {
			return true;
		}
		return false;
	}

	//metoda pentru numarul prim
	private boolean primeNumber(int number) {
		for(int i = 2; i <= number/2; i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}

	// metoda pentru paritate 
	private boolean isEven(int number) {
		if(number % 2 == 0) {
			return true;
		}
		return false;
	}

	// metoda pentru suma cifrelor divizibila cu 3
	private boolean digitSumDiv3(int number) {
		int digitsSum = 0;
		while (number != 0) {
			digitsSum += number % 10;
			number /= 10;
		}
		if(digitsSum % 3 == 0) {
			return true;
		}
		return false;
	}

	// 2.
	//metoda verificare damage
	private void verifyDamage(int health) {
		if(health <= 0) {
			lives -= 1;
			health = 100;
		}
	}

	//verificare viata
	private void liveStatus(int live) {
		if(live <= 0) {
			System.err.println("Game Over");
			return;
		}
	}

	public void findArtifact(int artifactCode) {

		if(perfectNumber(artifactCode)){
			score += 5_000;
			lives += 1;
			health = 100;
		}else if(primeNumber(artifactCode)) {
			score += 1_000;
			lives += 2;
			health += 25;
			if(health > 100) {
				health = 100;
			}
		}else if(isEven(artifactCode) && digitSumDiv3(artifactCode)) {
			score -= 3_000;
			health -= 25;
			lives -= 1;
			verifyDamage(health);
			liveStatus(lives);
		}else {
			score += artifactCode;
		}
	}

	//3.
	public boolean setWeaponInHand(String weaponInHand) {
		if(weaponInHand.equals("knife")) {
			if(score >= Knife) {
				this.weaponInHand = weaponInHand;
				score -= Knife;
				return true;
			}
		}else if(weaponInHand.equals("sniper")) {
			if(score >= Sniper) {
				this.weaponInHand = weaponInHand;
				score -= Sniper;
				return true;
			}
		}else if(weaponInHand.equals("kalashnikov")) {
			if(score >= Kalashnikov) {
				this.weaponInHand = weaponInHand;
				score -= Kalashnikov;
				return true;
			}
		}
		System.out.println("Jucatorul " + nickname + " nu a cumparat nici o arma");
		return false;
	}

	//4.
	public String getWeaponInHand() {
		return weaponInHand;
	}

	//5.
	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	//6.
	protected static String getGameName() {
		return gameName;
	}

	protected static void setGameName(String newGameName) {
		gameName = newGameName;
	}

	//7.
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	//8.
	public String getNickname() {
		return nickname;
	}

	//	public void setNickname(String nickname) {
	//		this.nickname = nickname;
	//	}

	//9.
	// metoda de calculare a distantei
	private double range(PlayerStatus player, PlayerStatus opponent) {
		return Math.sqrt(Math.pow((player.positionX - opponent.positionX), 2.0) 
				+ Math.pow((player.positionY - opponent.positionY), 2.0)); 		
	}

	private int probability(int health, int score) {
		return (3 * health + score / 1000) / 4;
	}

	public boolean shouldAttackOpponent(PlayerStatus player, PlayerStatus opponent) {
		if(player.getWeaponInHand() == opponent.getWeaponInHand()) {
			if(player.probability(health, score) > opponent.probability(health, score)) {
				System.out.println(player.getNickname() + " a castigat.");
				return true;
			}else if(player.probability(health, score) < opponent.probability(health, score)) { 
				System.out.println(opponent.getNickname() + "a castigat.");
				return false;
			} else {
				System.out.println("Sanse egale.");
			}

		} else if(player.getWeaponInHand() != opponent.getWeaponInHand()) {
			if(range(player, opponent) > 1_000) {
				if(player.getWeaponInHand().equals("sniper")) {
					System.out.println(player.getNickname() + " a castigat.");
					return true;
				} else if(player.getWeaponInHand().equals("kalashnikov") && opponent.getWeaponInHand().equals("knife")) {
					System.out.println(player.getNickname() + " a castigat.");
					return true;
				} else if(opponent.getWeaponInHand().equals("sniper")) {
					System.out.println(opponent.getNickname() + " a castigat.");
					return false;
				}else if(player.getWeaponInHand().equals(opponent.getWeaponInHand())) { 
					System.out.println("Sanse egale.");
					return false;
				}else {
					System.out.println(opponent.getNickname() + " a castigat.");
					return false;
				}
			}else if(range(player, opponent) <= 1_000) {
				if(player.getWeaponInHand().equals("kalashnikov")) {
					System.out.println(player.getNickname() + " a castigat.");
					return true;
				} else if(player.getWeaponInHand().equals("sniper") && opponent.getWeaponInHand().equals("knife")) {
					System.out.println(player.getNickname() + " a castigat.");
					return true;
				} else if(opponent.getWeaponInHand().equals("kalashnikov")) {
					System.out.println(opponent.getNickname() + " a castigat.");
					return false;
				}else if(player.getWeaponInHand().equals(opponent.getWeaponInHand())) { 
					System.out.println("Sanse egale.");
					return false;
				}else {
					System.out.println(opponent.getNickname() + " a castigat.");
					return false;
				}
			}
		}
		return false;
	}
}

