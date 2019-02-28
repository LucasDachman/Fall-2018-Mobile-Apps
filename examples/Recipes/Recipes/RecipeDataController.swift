//
//  FirebaseDataController.swift
//  Recipes
//
//  Created by Lucas Dachman on 2/26/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation
import Firebase

struct Recipe {
    init(id: String, name: String, url: String) {
        self.id = id
        self.name = name
        self.url = url
    }
    var id: String
    var url: String
    var name: String
}

class RecipeDataController {
    var dbRef: DatabaseReference!
    var recipeData = [Recipe]()
    var onDataUpdate: ((_ data: [Recipe]) -> Void)?
    
    func setupFirebaseListener() {
        dbRef = Database.database().reference().child("recipes")
        
        dbRef.observe(DataEventType.value, with: { snapshot in
            self.recipeData.removeAll()
            for snap in snapshot.children.allObjects as! [DataSnapshot]{
                let recipeID = snap.key
                if let recipeDict = snap.value as? [String: String], //get value as a Dictionary
                let recipeName = recipeDict["name"],
                let recipeURL = recipeDict["url"]
                {
                    let newRecipe = Recipe(id: recipeID, name: recipeName, url: recipeURL)
                    //add recipe to recipes array
                    self.recipeData.append(newRecipe)
                }
            }
            //passing the results to the onDataUpdate closure
            self.onDataUpdate?(self.recipeData)
        })
    }
    
    func addRecipe(named name: String, withURL url: String) {
        //create Dictionary
        let newRecipeDict = ["name": name, "url": url]
        
        //create a new ID
        let newLocRef = dbRef.childByAutoId()
        
        //write data to the new ID in Firebase
        newLocRef.setValue(newRecipeDict)
    }
    
    func deleteRecipe(withID id: String) {
        dbRef.child(id).removeValue()
    }
}
