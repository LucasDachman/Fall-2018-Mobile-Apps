//
//  APOD.swift
//  lab-4
//
//  Created by Lucas Dachman on 3/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct APOD: Decodable {
    let explanation: String
    let url: String
    let title: String
}
