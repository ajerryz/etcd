package notify_test

import (
	"testing"
	"time"

	"go.etcd.io/etcd/pkg/v3/notify"
)

func TestNotifier(t *testing.T) {

	notifier := notify.NewNotifier()
	closeChan := make(chan struct{})

	go func() {
	loop:
		for {
			select {
			case <-closeChan:
				t.Logf("receive close chan\n")
				break loop
			case <-notifier.Receive():
				t.Logf("receive close chan\n")
			}
		}
	}()

	time.Sleep(1 * time.Second)
	for i := 0; i < 5; i++ {
		notifier.Notify()
		time.Sleep(1 * time.Second)
	}
	t.Logf("main closed\n")
	close(closeChan)

	time.Sleep(1 * time.Second)

}
