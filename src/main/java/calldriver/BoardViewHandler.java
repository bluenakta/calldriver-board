package calldriver;

import calldriver.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardViewHandler {


    @Autowired
    private BoardRepository boardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCallReceived_then_CREATE_1 (@Payload CallReceived callReceived) {
        try {


            System.out.println(callReceived.getEventType());
            System.out.println(callReceived.getClass().getSimpleName());
            if (callReceived.isMe()) {

                System.out.println("22222222222222222222222222222222222");
                // view 객체 생성
                Board board = new Board();
                // view 객체에 이벤트의 Value 를 set 함
                board.setCallId(callReceived.getId());
                // view 레파지 토리에 save
                boardRepository.save(board);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("33333333333333333333333333333333333333");
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayed_then_UPDATE_1(@Payload Payed payed) {
        try {
            if (payed.isMe()) {
                // view 객체 조회
                Optional<Board> boardOptional = boardRepository.findById(payed.getCallId());
                if( boardOptional.isPresent()) {
                    Board board = boardOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setPayStatus(payed.getPayStatus());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_2(@Payload Canceled canceled) {
        try {
            if (canceled.isMe()) {
                // view 객체 조회
                List<Board> boardList = boardRepository.findByCallStatus(canceled.getCallStatus());
                for(Board board : boardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setId(canceled.getId());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDriverAssigned_then_UPDATE_3(@Payload DriverAssigned driverAssigned) {
        try {
            if (driverAssigned.isMe()) {
                // view 객체 조회
                Optional<Board> boardOptional = boardRepository.findById(driverAssigned.getCallId());
                if( boardOptional.isPresent()) {
                    Board board = boardOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setDriverId(driverAssigned.getId());
                    board.setDriverStatus(driverAssigned.getStatus());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDriverAssignFailed_then_UPDATE_4(@Payload DriverAssignFailed driverAssignFailed) {
        try {
            if (driverAssignFailed.isMe()) {
                // view 객체 조회
                Optional<Board> boardOptional = boardRepository.findById(driverAssignFailed.getCallId());
                if( boardOptional.isPresent()) {
                    Board board = boardOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setCallStatus(driverAssignFailed.getStatus());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}