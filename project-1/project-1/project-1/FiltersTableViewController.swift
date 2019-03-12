//
//  FiltersTableViewController.swift
//  project-1
//
//  Created by Lucas Dachman on 3/8/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

let reuseIdentifier = "filterCell"

class FiltersTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.navigationItem.rightBarButtonItem = self.editButtonItem
        tableView.delegate = self
        tableView.dataSource = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        tableView.reloadData()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return ImageEditor.appliedFilters.count + 1
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath)
        
        if indexPath.row == tableView.numberOfRows(inSection: 0) - 1 {
            cell.textLabel?.text = "Add"
            cell.textLabel?.textAlignment = .center
            cell.backgroundColor = UIColor.groupTableViewBackground
            cell.selectionStyle = .default
//            cell.isUserInteractionEnabled = true
        } else {
            cell.textLabel?.text = ImageEditor.appliedFilters[indexPath.row].displayName
            cell.textLabel?.textAlignment = .justified
            cell.backgroundColor = UIColor.white
            cell.selectionStyle = .none
//            cell.isUserInteractionEnabled = false
        }

        return cell
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            ImageEditor.appliedFilters.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }

    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        //TODO: see if this actually works
        ImageEditor.appliedFilters.swapAt(fromIndexPath.row, to.row)
    }

    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // exclude "add" row
        if indexPath.row == tableView.numberOfRows(inSection: 0) - 1 {
            return false
        } else {
            return true
        }
    }
    
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // exclude "add" row
        if indexPath.row == tableView.numberOfRows(inSection: 0) - 1 {
            return false
        } else {
            return true
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
