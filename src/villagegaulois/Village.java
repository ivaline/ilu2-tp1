package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Marche marche;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtals;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}
	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //		
	private static class Marche{
		private Etal[] etals;
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i=0; i<nbEtals; i++) {
				this.etals[i] = new Etal();
		 }
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!this.etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		Etal[] trouverEtals(String produit) {
			int indiceNewTab = 0;
			for (int i=0; i<etals.length; i++) {
				System.out.println(this.etals[i].getVendeur().getNom());
				if (this.etals[i].contientProduit(produit)) {
					indiceNewTab++;
				}
			}
			Etal[] etalsProduit = new Etal[indiceNewTab];
			indiceNewTab = 0;
			for (int i=0; i<etals.length; i++) {
				if (this.etals[i].contientProduit(produit)) {
					etalsProduit[indiceNewTab] = this.etals[i];
					indiceNewTab++;
				}
			}
			return etalsProduit;
		}
		Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (this.etals[i].getVendeur() == gaulois) {
					return this.etals[i];
				}
			}
			return null;
		}
			
		 String afficherMarche(){
			 int nbEtalVide = etals.length;
			 for (int i=0; i<etals.length; i++) {
					if (this.etals[i].isEtalOccupe()) {
						System.out.println(this.etals[i].afficherEtal());
						nbEtalVide --;
					}
				}
			 
			return "Il reste " +nbEtalVide + " �tals non utilis�s dans le march�.\n";
		 }
			
		}
	
	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //	
	
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois gaulois, String produit ,int nbProduit){
		StringBuilder chaine = new StringBuilder();
		chaine.append(gaulois.getNom() + " cherche un endroit pour vendre " + nbProduit+ " " + produit+"\n");
		int numEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(numEtal, gaulois, produit, nbProduit);
		chaine.append("Le vendeur " + gaulois.getNom() + " vend des "+ produit + " à l'étal n° "+ numEtal + "\n" + "\n");
		return chaine.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etals = marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des fleurs sont :\r\n");
		for (Etal eta : etals) {
			Gaulois vend = eta.getVendeur();
			chaine.append("- " + vend);}
		return chaine.toString();
	}
}