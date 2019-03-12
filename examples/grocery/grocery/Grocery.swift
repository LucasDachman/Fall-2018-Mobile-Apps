//
//  Grocery.swift
//  grocery
//
//  Created by Lucas Dachman on 3/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation
import RealmSwift

class GroceryItem: Object {
    @objc dynamic var name = ""
    @objc dynamic var bought = false
}
