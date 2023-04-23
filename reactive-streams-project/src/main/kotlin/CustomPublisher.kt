import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

class CustomPublisher: Publisher<Int> {

    private val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun subscribe(s: Subscriber<in Int>?) {
        println("구독자 : 신문사야 나 너희 신문 볼께")
        println("신문사 : 구독 정보를 만들어서 줄테니 기다려")
        val subscription = CustomSubscription(s, nums.iterator())
        println("신문사 : 구독 정보 생성 완료")
        s?.onSubscribe(subscription)
    }
}