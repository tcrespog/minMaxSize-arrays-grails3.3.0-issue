import grails.testing.gorm.DomainUnitTest
import sampleapp.Sample
import spock.lang.Specification

class SampleUnitSpec  extends Specification implements DomainUnitTest<Sample> {

    void "passing test"() {
        given:
        Sample sample = new Sample(field: 'text', bytes: 'aa'.bytes, stringArray: ['a', 'a'].toArray()).save()

        expect:
        sample.validate()
    }

    void "byte array size  exceeds max size"() {
        given:
        int forbiddenSize = (1024 * 1024 * 10 + 1)
        Sample sample = new Sample(field: 'text', bytes: ('a' * forbiddenSize).bytes, stringArray: ['a', 'a'].toArray()).save()

        expect:
        !sample.validate()
    }

    void "string array size exceeds max size"() {
        given:
        Sample sample = new Sample(field: 'text', bytes: 'aa'.bytes, stringArray: ['a', 'a', 'a', 'a'].toArray()).save()

        expect:
        !sample.validate()
    }

    void "string array size is lower than min size"() {
        given:
        Sample sample = new Sample(field: 'text', bytes: 'aa'.bytes, stringArray: ['a'].toArray()).save()

        expect:
        !sample.validate()
    }

}
