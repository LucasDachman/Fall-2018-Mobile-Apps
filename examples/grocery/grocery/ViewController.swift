//
//  ViewController.swift
//  grocery
//
//  Created by Lucas Dachman on 3/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    let gdc = GroceryDataController()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        gdc.onDataUpdate = {[weak self] (results: [GroceryItem]) in
            self?.tableView.reloadData()
        }
        gdc.setupDB()
    }
    
    @IBAction func onAdd(_ sender: Any) {
        let addalert = UIAlertController(title: "New Item", message: "Add a new item to your grocery list", preferredStyle: .alert)
            //add textfield to the alert
            addalert.addTextField(configurationHandler: {(UITextField) in
            })
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
        addalert.addAction(cancelAction)
        let addItemAction = UIAlertAction(title: "Add", style: .default, handler: { (UIAlertAction) in
            // adds new item
            let newitem = addalert.textFields![0] //gets textfield
            let newGroceryItem = GroceryItem() //create new Grocery instance
            newGroceryItem.name = newitem.text! //set name with textfield text
            newGroceryItem.bought = false
            self.gdc.add(item: newGroceryItem)
        })
        addalert.addAction(addItemAction)
        present(addalert, animated: true, completion: nil)
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return gdc.groceryItems.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        let item = gdc.groceryItems[indexPath.row]
        cell.textLabel?.text = item.name
        cell.accessoryType = item.bought ? .checkmark : .none
        return cell
    }
    
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        gdc.toggleBought(at: indexPath.row)
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            gdc.deleteItem(at: indexPath.row)
        }
    }


}

