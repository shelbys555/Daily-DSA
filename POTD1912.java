class MovieRentingSystem {
    // Helper class
    static class Entry {
        int shop;
        int movie;
        int price;
        Entry(int s, int m, int p) { shop = s; movie = m; price = p; }
    }

    // Comparator for available/unrented: by price, then shop
    Comparator<Entry> cmpAvail = (a, b) -> {
        if (a.price != b.price) return a.price - b.price;
        return a.shop - b.shop;
    };

    // Comparator for rented: price, then shop, then movie
    Comparator<Entry> cmpRented = (a, b) -> {
        if (a.price != b.price) return a.price - b.price;
        if (a.shop != b.shop) return a.shop - b.shop;
        return a.movie - b.movie;
    };

    // movie → set of available entries
    Map<Integer, TreeSet<Entry>> availableByMovie;

    // global set of rented entries
    TreeSet<Entry> rentedSet;

    // map to get price given shop,movie
    Map<Long, Integer> priceOf;  // use a composite key, e.g. shop * MAX_MOVIES + movie or some Pair class

    public MovieRentingSystem(int n, int[][] entries) {
        availableByMovie = new HashMap<>();
        rentedSet = new TreeSet<>(cmpRented);
        priceOf = new HashMap<>();
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            priceOf.put(getKey(shop, movie), price);
            availableByMovie
              .computeIfAbsent(movie, k -> new TreeSet<>(cmpAvail))
              .add(new Entry(shop, movie, price));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> ans = new ArrayList<>();
        if (!availableByMovie.containsKey(movie)) return ans;
        Iterator<Entry> it = availableByMovie.get(movie).iterator();
        while (it.hasNext() && ans.size() < 5) {
            ans.add(it.next().shop);
        }
        return ans;
    }

    public void rent(int shop, int movie) {
        int price = priceOf.get(getKey(shop, movie));
        Entry e = new Entry(shop, movie, price);
        // remove from available
        availableByMovie.get(movie).remove(e);
        // add to rented
        rentedSet.add(e);
    }

    public void drop(int shop, int movie) {
        int price = priceOf.get(getKey(shop, movie));
        Entry e = new Entry(shop, movie, price);
        // remove from rented
        rentedSet.remove(e);
        // add back to available
        availableByMovie.get(movie).add(e);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> ans = new ArrayList<>();
        Iterator<Entry> it = rentedSet.iterator();
        while (it.hasNext() && ans.size() < 5) {
            Entry e = it.next();
            ans.add(Arrays.asList(e.shop, e.movie));
        }
        return ans;
    }
    
    private long getKey(int shop, int movie) {
        return ((long)shop << 32) | movie;
    }
}
