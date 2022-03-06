import static com.google.common.truth.Truth.assertThat;
import org.testng.annotations.Test;

public class CardTest {
    Card queenClubs = new Card(CardSuit.CLUB, CardNumber.QUEEN);
    Card nineHeart=new Card(CardSuit.HEART,CardNumber.NINE);
    Card fourDiamond=new Card(CardSuit.DIAMOND,CardNumber.FOUR);
    Card aceSpade=new Card(CardSuit.SPADE,CardNumber.ACE);
    @Test
    public void numTest() {
        assertThat(queenClubs.getNumber()).isEqualTo(CardNumber.QUEEN);
        assertThat(nineHeart.getNumber()).isEqualTo(CardNumber.NINE);
        assertThat(fourDiamond.getNumber()).isEqualTo(CardNumber.FOUR);
        assertThat(aceSpade.getNumber()).isEqualTo(CardNumber.ACE);

    }
    @Test
    public void colorTest() {
        //Black card case
        assertThat(queenClubs.isBlack(queenClubs.getSuit()));
        assertThat(aceSpade.isBlack(aceSpade.getSuit()));
        //Red card case
        assertThat(nineHeart.isRed(nineHeart.getSuit()));
        assertThat(fourDiamond.isRed(fourDiamond.getSuit()));
        //handles case where they aren't equivalent
        assertThat(queenClubs.isRed(queenClubs.getSuit())).isFalse();
        assertThat(nineHeart.isBlack(nineHeart.getSuit())).isFalse();
        assertThat(fourDiamond.isBlack(fourDiamond.getSuit())).isFalse();
        assertThat(aceSpade.isBlack(aceSpade.getSuit())).isFalse();

    }
}
