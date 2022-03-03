
import { render, screen} from '@testing-library/react';
import ToDoItem from "./ToDoItem";

test("To-DoItem ist renderd Correctly", () =>{
    //given
    const demoitem = {
        formattedEndDate: "24 03 2022",
        name: "Han Solo",
        description: "Star Wars schauen",
        id: "12345g",
        status: false
    };

    // when
render(<ToDoItem item={demoitem} getData={() => {}}/>)

    const result = Array.from(screen.getByTestId('the-item').querySelectorAll("div"))
    expect(result[1].textContent).toEqual("itemName Han Solo")
    expect(result[3].textContent).toEqual(" itemDeadline 24 03 2022")
    expect(result[4].textContent).toEqual("itemID 12345g")
})