//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        BankAccountBuilder builder = new BankAccountBuilder(12345l);

        BankAccount bankAccount = builder.withBalance(1000.20)
                .withOwner("Oaken")
                .withRate(10.15)
                .withType(BankAccountType.PLATINUM)
                .build();

        System.out.println(bankAccount);
    }
}
