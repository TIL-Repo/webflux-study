import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class CustomSubscriber: Subscriber<Int> {

    var bufferSize = 2L

    private lateinit var subscription: Subscription

    override fun onSubscribe(s: Subscription?) {
        println("구독자 : 구독 정보 잘 받았어")
        subscription = s!!
        println("구독자 : 나 이제 신문 ${bufferSize}개씩 줘")
        subscription.request(bufferSize)
    }

    override fun onError(t: Throwable?) {
        println("구독중 에러")
    }

    override fun onComplete() {
        println("구독 완료")
    }

    override fun onNext(t: Int?) {
        println("onNext() : $t")
        bufferSize -= 1
        if (bufferSize == 0L) {
            println("하루 지남")
            bufferSize = 2
            subscription.request(bufferSize)
        }
    }
}