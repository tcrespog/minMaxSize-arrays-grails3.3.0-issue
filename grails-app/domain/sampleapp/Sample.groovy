package sampleapp

class Sample {

    String field
    byte[] bytes
    String[] stringArray

    static constraints = {
        bytes(minSize: 1, maxSize: 1024 * 1024 * 10)
        stringArray(minSize: 2, maxSize: 3)
    }
}
