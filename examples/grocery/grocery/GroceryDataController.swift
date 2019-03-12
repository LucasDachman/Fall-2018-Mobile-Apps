//
//  GroceryDataController.swift
//  grocery
//
//  Created by Lucas Dachman on 3/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation
import RealmSwift

class GroceryDataController {
    var gRealm : Realm!
    
    var groceryResults: Results<GroceryItem> {
        return gRealm.objects(GroceryItem.self)
    }
    
    var groceryItems: [GroceryItem] {
        return Array(groceryResults)
    }
    
    var onDataUpdate: ((_ results: [GroceryItem]) -> Void)?
    
    func setupDB() {
        do {
            gRealm = try Realm()
        } catch let error {
            print(error.localizedDescription)
        }
        onDataUpdate?(Array(groceryResults))
        print(Realm.Configuration.defaultConfiguration.fileURL!)
    }
    
    func add(item:GroceryItem) {
        do {
            try self.gRealm.write({
                self.gRealm.add(item) //add to realm database
            })
        } catch let error{
            print(error.localizedDescription)
        }
        onDataUpdate?(groceryItems)
    }
    
    func toggleBought(at index: Int) {
        try! self.gRealm.write {
            groceryItems[index].bought.toggle()
        }
        onDataUpdate?(groceryItems)
    }
    
    func deleteItem(at index: Int) {
        try! self.gRealm.write {
            self.gRealm.delete(groceryItems[index])
        }
        onDataUpdate?(groceryItems)
    }
    
}
