import type InputForm from "@/components/Forms/InputForm.vue";
import type SelectForm from "@/components/Forms/SelectForm.vue";
import { AlertType } from "@/types/Alert";
import type { ErrorMap } from "@/types/utils/ErrorMap";
import { ValidationError } from "@/types/utils/ValidationError";
import type { Ref } from "vue";

export type InputFormType = InstanceType<typeof InputForm>;
export type SelectFormType = InstanceType<typeof SelectForm>;

export type AnyInputFormType = InputFormType | SelectFormType;

const validationErrors = Object.entries(ValidationError);

export function setValidationErrorForm(
  formInputMap: Map<string, Ref<AnyInputFormType | undefined>>,
  errorMap: ErrorMap
) {
  for (const [field, errors] of Object.entries(errorMap)) {
    const input = formInputMap.get(field);
    if (!input?.value) continue;

    const errorMessages = errors
      .map((v) => validationErrors.find(([k]) => k == v)?.[1] ?? v)
      .sort();

    if (input.value.setAlert) {
      input.value.setAlert({
        message: errorMessages.join(", "),
        type: AlertType.ERROR,
      });
    }
  }
}
