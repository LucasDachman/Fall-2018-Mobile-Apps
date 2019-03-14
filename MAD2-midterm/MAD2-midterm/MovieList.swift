//
//  MovieList.swift
//  MAD2-midterm
//
//  Created by Lucas Dachman on 3/14/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct Movie {
    var name = String()
    var url = String()
    
    init(name: String, url: String) {
        self.name = name
        self.url = url
    }
}

class MovieList {

    init() {
        mMovies.append(Movie(name: "Godfather", url: "https://www.imdb.com/title/tt0068646"))
    }
    
    static var filter = String()
    static var instance = MovieList()
    var mMovies = [Movie]()
    
    static var movies: [Movie] {
        if filter.isEmpty || filter == "" {
            return instance.mMovies
        } else {
            return instance.mMovies.filter {$0.name.lowercased().contains(filter.lowercased())}
        }
    }
    
    static func add(_ movie: Movie) {
        instance.mMovies.append(movie)
    }
    
    static func remove(at i: Int) {
        instance.mMovies.remove(at: i)
    }
    
}
