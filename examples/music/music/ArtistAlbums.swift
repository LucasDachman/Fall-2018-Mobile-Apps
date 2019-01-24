//
//  ArtistAlbums.swift
//  music
//
//  Created by Lucas Dachman on 1/24/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct ArtistAlbums: Decodable {
    let name: String
    let albums: [String]
}
