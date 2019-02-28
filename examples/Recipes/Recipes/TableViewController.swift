//
//  ViewController.swift
//  Recipes
//
//  Created by Lucas Dachman on 2/26/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    let recipeData = RecipeDataController()

    override func viewDidLoad() {
        super.viewDidLoad()
        recipeData.onDataUpdate = {[weak self] (data: [Recipe]) in
            self?.render()
        }
        recipeData.setupFirebaseListener()
    }
    
    func render() {
        tableView.reloadData()
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return recipeData.recipeData.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = recipeData.recipeData[indexPath.row].name
        return cell
    }

    @IBAction func unwindToTableView(_ unwindSegue: UIStoryboardSegue) {

    }

}

