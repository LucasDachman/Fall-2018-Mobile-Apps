//
//  ViewController.swift
//  countries
//
//  Created by Lucas Dachman on 2/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    var continentList = [String]()
    let continentData = ContinentDataModelController()

    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.prefersLargeTitles = true
        continentData.loadData()
        continentList = continentData.getContinents()
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return continentList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CountryIdentifier", for: indexPath)
        cell.textLabel?.text = continentList[indexPath.row]
        return cell
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "countrySegue" {
            let detailVC = segue.destination as! DetailTableViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            //sets the data for the destination controller
            detailVC.title = continentList[indexPath.row]
            detailVC.continentData = continentData
            detailVC.selectedContinent = indexPath.row
            
        }
    }

}

