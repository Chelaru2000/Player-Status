package player;
// o functie pentru afisare
public class Main {

	public static void main(String[] args) {
		PlayerStatus Mihai = new PlayerStatus ();
		Mihai.initPlayer("Mihai");
		
		PlayerStatus Andrei = new PlayerStatus ();
		Andrei.initPlayer("Andrei", 2, 50_000);
		
		
		
		System.out.println(Mihai.getNickname());
		Mihai.findArtifact(6);
		Mihai.findArtifact(6);
		System.out.println("health: " + Mihai.health + "\n" + "lives: " + Mihai.lives + "\n" + "score: " + Mihai.score);
	
		Mihai.setWeaponInHand("sniper");
		System.out.println("health: " + Mihai.health + "\n" + "lives: " + Mihai.lives + "\n" + "score: " + Mihai.score);
		
		Mihai.findArtifact(18);
		System.out.println("health: " + Mihai.health + "\n" + "lives: " + Mihai.lives + "\n" + "score: " + Mihai.score);
		
		Mihai.findArtifact(18);
		System.out.println("health: " + Mihai.health + "\n" + "lives: " + Mihai.lives + "\n" + "score: " + Mihai.score);
		
		Andrei.setWeaponInHand("knife");
		Mihai.setWeaponInHand("sniper");
		Mihai.setPositionX(20);
		Mihai.setPositionY(30);
		Andrei.setPositionX(30);
		Andrei.setPositionY(45);
		System.out.println(Mihai.getWeaponInHand());
		System.out.println(Mihai.shouldAttackOpponent(Mihai, Andrei));
	
	}
}
