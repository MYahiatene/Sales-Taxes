<template>
    <v-app id="inspire">
        <v-system-bar app>
            <v-spacer></v-spacer>

        </v-system-bar>

        <v-app-bar app>

            <v-spacer></v-spacer>
            <v-toolbar-title>Sales Taxes Application</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-navigation-drawer
            fixed
            temporary
        >
        </v-navigation-drawer>

        <v-main class="grey lighten-1">
            <v-container>
                <v-row>
                    <template>
                        <v-col>
                            <v-card
                                class="mx-auto"
                                color="grey lighten-2"
                                max-width="800"
                            >

                                <v-list-item three-line>
                                    <v-list-item-content>
                                        <v-list-item-title class="text-h3 black--text mb-1">
                                            Shopping Basket
                                        </v-list-item-title>

                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item v-for="item in items" :key="item">

                                    <v-list-item-content>
                                        <div class="text-h5 black--text mb-1">
                                            {{ item }}
                                        </div>

                                    </v-list-item-content>


                                </v-list-item>
                                <v-list-item>

                                    <v-list-item-content v-show="totalSalesTax!==0.00" v-model="totalSalesTax">
                                        <div class="text-h5 black--text mb-1">
                                            Sales Taxes:{{ totalSalesTax }}
                                        </div>

                                    </v-list-item-content>


                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-content v-show="totalPrice!==0.00" v-model="totalPrice">
                                        <div class="text-h5 black--text mb-1">
                                            Total:{{ totalPrice }}
                                        </div>

                                    </v-list-item-content>
                                </v-list-item>
                                <v-card-actions>
                                    <v-container fluid>
                                        <v-form ref="form" v-model="valid">
                                            <v-text-field id="textField"
                                                          v-model="input"
                                                          :rules="inputRules"
                                                          class="text-h7 black--text mb-1"

                                            ></v-text-field>
                                        </v-form>
                                    </v-container>
                                    <v-btn
                                        outlined
                                        rounded
                                        text
                                        class="blue-grey"

                                        @click="addItem(input)"
                                    >
                                        Add item to basket
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>

                    </template>
                </v-row>
            </v-container>
            <div>
                <v-snackbar
                    v-model="snackbar"
                    :timeout="timeout"
                >
                    {{ text }}

                    <template #action="{ attrs }">
                        <v-btn
                            color="blue"
                            text
                            v-bind="attrs"
                            @click="snackbar = false"
                        >
                            Close
                        </v-btn>
                    </template>
                </v-snackbar>
            </div>
            <v-container>
                <v-row>
                    <v-spacer></v-spacer>
                    <v-btn @click="deleteBasket">Delete Basket</v-btn>
                    <v-spacer></v-spacer>
                </v-row>

            </v-container>

        </v-main>

    </v-app>
</template>

<script>
export default {
    data: () => ({
        items: [],
        input: "",
        valid: false,
        snackbar: false,
        timeout: 1500,
        totalSalesTax: 0.00,
        totalPrice: 0.00,
        basketItem: {},
        text: "Error! Wrong Input!",
        inputRules: [v => v.length > 0, v => v.split(" ").filter(el => /\S/.test(el)).length > 3, v => Number.isInteger(Number(v.split(" ")[0]))

        ],

    }),
    created() {
        this.$axios.defaults.baseURL = 'http://localhost:8088/v1/api';
    },
    methods: {
        checkIfBasicTax(input) {
            return !(input.includes("book") || input.includes("pills") || input.includes("chocolate"))

        },
        async addItem(input) {
            if (this.valid) {
                input = input.trim();
                const inputArr = input.split(" ").filter(el => /\S/.test(el));
                const amount = inputArr[0];
                const importTax = !!input.includes("imported");
                const basicTax = this.checkIfBasicTax(input)
                const price = inputArr[inputArr.length - 1]
                let name = input.split(" ").filter(el => /\S/.test(el));
                name.splice(0, 1)
                name.pop()
                name = name.filter(el => el !== "at")
                name = name.filter(el => el !== "imported")
                name = name.join(" ")
                const item = {name, amount, basicTax, importTax, price};
                const response = await this.$axios.post("/addItem", item);
                const values = response.data;
                this.items.push(this.createBasketItem(values));
                this.totalSalesTax = Number(Math.round((this.totalSalesTax + values.amount * values.salesTaxes) + "e2") + "e-2");
                this.totalPrice = Number(Math.round((this.totalPrice + values.amount * values.priceWithTax) + "e2") + "e-2");
            } else {
                this.snackbar = true;
            }
        },
        createBasketItem(item) {
            const basketItem = `${item.amount} ${item.importTax ? "imported" : ""} ${item.name}: ${item.amount * item.priceWithTax.toFixed(2)}`
            return basketItem;
        }
        ,
        deleteBasket() {
            this.totalPrice = 0.00;
            this.totalSalesTax = 0.00;
            this.items = []
            this.input = "";
            this.$axios("/deleteBasket");
        },
    }
    ,
}
</script>
<style>
#textField {
    color: black !important;

}

</style>
