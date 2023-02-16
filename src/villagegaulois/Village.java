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
		this.nbEtals = nbEtals;
	}
	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //	
// ------------------------------------------------PARTIE MARCHE ----------------------------------------------------------------- //		
	private class Marche{
		private Etal[] etals;
		private Marche() {
			this.etals = new Etal[nbEtals];
			for (int i=0; i<nbEtals; i++) {
				this.etals[i] = new Etal();
		 }
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for (int i=0; i<nbEtals; i++) {
				if (!this.etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		Etal[] trouverEtals(String produit) {
			int indiceNewTab = 0;
			for (int i=0; i<nbEtals; i++) {
				if (this.etals[i].contientProduit(produit)) {
					indiceNewTab++;
				}
			}
			Etal[] etalsProduit = new Etal[indiceNewTab];
			indiceNewTab = 0;
			for (int i=0; i<nbEtals; i++) {
				if (this.etals[i].contientProduit(produit)) {
					etalsProduit[indiceNewTab] = this.etals[i];
					indiceNewTab++;
				}
			}
			return etalsProduit;
		}
		Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<nbEtals; i++) {
				if (this.etals[i].getVendeur() == gaulois) {
					return this.etals[i];
				}
			}
			return null;
		}
			
		 String afficherMarche(){
			 int nbEtalVide = nbEtals;
			 for (int i=0; i<nbEtals; i++) {
					if (this.etals[i].isEtalOccupe()) {
						System.out.println(this.etals[i].afficherEtal());
						nbEtalVide --;
					}
				}
			 
			return "Il reste " +nbEtalVide + " étals non utilisés dans le marché.\n";
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
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}