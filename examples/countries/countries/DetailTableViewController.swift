//
//  DetailTableViewController.swift
//  countries
//
//  Created by Lucas Dachman on 2/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class DetailTableViewController: UITableViewController {

    var continentData = ContinentDataModelController()
    var selectedContinent = 0
    var countryList = [String]()
    var addedCountry = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

//         self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    override func viewWillAppear(_ animated: Bool) {
        countryList = continentData.getCountries(forContinent: selectedContinent)
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return countryList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CountryIdentifier", for: indexPath)
        cell.textLabel?.text = countryList[indexPath.row]
        return cell
    }

    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            countryList.remove(at: indexPath.row)
            continentData.removeCountry(fromContinent: selectedContinent, countryIndex: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
            
        }    
    }

    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let from = fromIndexPath.row
        let to = to.row
        countryList.swapAt(from, to)
        continentData.swapCountries(forContinent: selectedContinent, from, to)
    }

    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    
    @IBAction func unwind(_ unwindSegue: UIStoryboardSegue) {
        let sourceVC = unwindSegue.source as! AddCountryViewController
        if unwindSegue.identifier == "done" && !sourceVC.newCountryName.isEmpty {
            continentData.addCountry(continentIndex: selectedContinent, countryName: sourceVC.newCountryName)
            countryList.append(sourceVC.newCountryName)
            tableView.reloadData()
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
