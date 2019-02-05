//
//  ContinentDataModel.swift
//  countries
//
//  Created by Lucas Dachman on 2/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct ContinentDataModel: Codable {
    var continent: String
    var countries: [String]
}

class ContinentDataModelController {
    var allData = [ContinentDataModel]()
    let fileName = "continents2"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decodes the property list
                allData = try plistdecoder.decode([ContinentDataModel].self, from: data)
            } catch {
                // handle error
                print(error)
            }
        }
    }
    
    func getContinents() -> [String] {
        return allData.map {$0.continent}
    }
    
    func getCountries(forContinent index: Int) -> [String] {
        return allData[index].countries
    }
    
    func addCountry(continentIndex continent: Int, countryName name: String, atIndex i: Int) {
        allData[continent].countries.insert(name, at: i)
    }
    
    func removeCountry(fromContinent continent: Int, countryIndex i: Int) {
        allData[continent].countries.remove(at: i)
    }
}
