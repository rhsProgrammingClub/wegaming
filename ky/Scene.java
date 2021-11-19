package ky;

import java.util.ArrayList;

public class Scene {

    private ArrayList<ArrayList<Asset>> assetLayers = new ArrayList<ArrayList<Asset>>(); // this is a collection of arraylists, which are layers

	private ArrayList<ArrayList<Entity>> entityLayers = new ArrayList<ArrayList<Entity>>(); // holds all the entities to be rendered
	
	private ArrayList<CollisionEntity> collisionEntities = new ArrayList<CollisionEntity>(); // holds all collision entities to handle collisions easier

	public void add(Asset asset) {
		int difference = asset.getLayer() + 1 - assetLayers.size();// check if the indicated layer exists or not
		if(difference > 0) { 							// if difference is greater than 0,
			for(int i = 0; i < difference; i++) {		// there needs to be filler layers to reach the indicated layer
				assetLayers.add(new ArrayList<Asset>());
			}
		}
		if(!assetLayers.get(asset.getLayer()).contains(asset)) { // add to layer
			assetLayers.get(asset.getLayer()).add(asset);
		}
	}
	
	public void add(Entity entity) {
		
		int difference = entity.getLayer() + 1 - entityLayers.size();// check if the indicated layer exists or not
		if(difference > 0) { 							// if difference is greater than 0,
			for(int i = 0; i < difference; i++) {		// there needs to be filler layers to reach the indicated layer
				entityLayers.add(new ArrayList<Entity>());
			}
		}
		if(!entityLayers.get(entity.getLayer()).contains(entity)) { // add to layer
			entityLayers.get(entity.getLayer()).add(entity);
		}
		
		// this keeps track of all collision entities
		if(entity instanceof CollisionEntity && !collisionEntities.contains(entity)) {
			collisionEntities.add((CollisionEntity) entity);
			//((CollisionEntity) entity).onCollision();
		}
	}

	public Entity[][] getEntityLayers(){
		Entity[][] converted = new Entity[entityLayers.size()][];
		for(int i = 0; i < entityLayers.size(); i++) {
			converted[i] = entityLayers.get(i).toArray(new Entity[entityLayers.get(i).size()]);
		}
		return converted;
	}
	
	public Asset[][] getAssetLayers(){ // gets all assets whilst keeping the layers
		Asset[][] converted = new Asset[assetLayers.size()][];
		for(int i = 0; i < assetLayers.size(); i++) {
			converted[i] = assetLayers.get(i).toArray(new Asset[assetLayers.get(i).size()]);
		}
		return converted;
	}
	
	public CollisionEntity[] getCollisionEntities() {
		return collisionEntities.toArray(new CollisionEntity[collisionEntities.size()]);
	}


    
}