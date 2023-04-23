import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class CustomSubscription(
    private val subscriber: Subscriber<in Int>?,
    private val nums: Iterator<Int>
): Subscription {

    override fun request(n: Long) {
        var cnt = n
        while (cnt-- > 0) {
            if (nums.hasNext()) {
                subscriber?.onNext(nums.next())
            }
            else {
                subscriber?.onComplete()
                break
            }
        }
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}