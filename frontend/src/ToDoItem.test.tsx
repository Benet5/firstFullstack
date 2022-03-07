
import {render, screen, waitFor} from '@testing-library/react';
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


test('To-Do-item could not be checked', async () => {
        jest.spyOn(global, 'fetch').mockImplementation((URL: string) => {
            expect(URL).toEqual("http://localhost:8080/todoapp/checkitemid/12345g")
            return Promise.resolve({
                ok: false,
            } as Response)
        })

        const demoitem = {
            formattedEndDate: "24 03 2022",
            name: "Han Solo",
            description: "Star Wars schauen",
            id: "12345g",
            status: false
        }

        render(<ToDoItem item={demoitem} getData={() => {
        }}/>)

        const Button = screen.getByTestId("chekcbuttontest") as HTMLButtonElement;
        Button.click();

        await waitFor(() => {
            expect(screen.getByTestId("errorItem").textContent).toEqual("Failed to check item.")
        });

    }
)

test('To-Do-item could not be deleted', async () => {
        jest.spyOn(global, 'fetch').mockImplementation((URL: string) => {
            expect(URL).toEqual("http://localhost:8080/todoapp/deleteitem/Han Solo")
            return Promise.resolve({
                ok: false,
            } as Response)
        })

        const demoitem = {
            formattedEndDate: "24 03 2022",
            name: "Han Solo",
            description: "Star Wars schauen",
            id: "12345g",
            status: false
        }

        render(<ToDoItem item={demoitem} getData={() => {
        }}/>)

        const Button = screen.getByTestId("deletebuttontest") as HTMLButtonElement;
        Button.click();

        await waitFor(() => {
            expect(screen.getByTestId("errorItem").textContent).toEqual("Failed to delete item.")
        });

    }
)

